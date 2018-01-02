package com.wing.ws.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.google.inject.Inject;
import com.wing.dao.DAO;
import com.wing.model.Cartucho;
import com.wing.model.Categoria;
import com.wing.model.Impressora;
import com.wing.model.Marca;
import com.wing.util.HashUtil;
import com.wing.util.Parameter;

@MultipartConfig
public class FileServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DAO dao = null;
		Parameter param = null;
		String queryName = null;
		
		String modelo = req.getParameter("modelo");
		
		Categoria cat = null;
		
		if(req.getParameter("categoria.id")!=null) {
			Integer catId = Integer.parseInt(req.getParameter("categoria.id"));
			cat = new Categoria();
			cat.setId(catId);
		}
		String type = req.getParameter("type");
		if(type.equals("impressora")) {
			Impressora impressora = new Impressora();
			impressora.setModelo(modelo);
			impressora.setCategoria(cat);
			
			String img = writeToFile(type, req);
			impressora.setImagem(img);
			System.out.println(img);
			
			resp.getWriter().write(img);
			param = new Parameter<>("pCatId", cat.getId());
			queryName = "impByCategoria";
			dao = new DAO<Impressora>(Impressora.class);
			dao.insert(impressora);
		}else if(type.equals("cartucho")){
			Cartucho cartucho = new Cartucho();
			cartucho.setModelo(modelo);
			Impressora imp = null;
			if(req.getParameter("impressora.id")!=null) {
				Integer impId = Integer.parseInt(req.getParameter("impressora.id"));
				imp = new Impressora();
				imp.setId(impId);
			}
			cartucho.setImpressora(imp);
			String img = writeToFile(type ,req);
			cartucho.setImagem(img);
			param = new Parameter("pImpId", imp.getId());
			queryName = "cartuchosByImp";
			dao = new DAO<Cartucho>(Cartucho.class); 
			dao.insert(cartucho);
		}
//			Jsonb
			//org.codehaus.jackson.JsonGenerator
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			String json = mapper.writeValueAsString(dao.listByQuery(queryName, Arrays.asList(param)));
			resp.setContentType(MediaType.APPLICATION_JSON);
			PrintWriter out = resp.getWriter();
			out.println(json);
			
	}
	
	
	private String writeToFile(String tipo, HttpServletRequest req) throws IOException, ServletException {
		String img = "";
		
		try {
			Part part = req.getPart("file");
			if(part!=null) {
				InputStream input = part.getInputStream();
				String type = req.getParameter("fileType");
				img = tipo+"_"+HashUtil.hashGenerator()+type;
				String path = "C:/xampp/htdocs/impressora/img/"+img;

				FileOutputStream  stream = new FileOutputStream(new File(path));
				int size = (int)part.getSize();
				System.out.println(size);
                int read = 0;
                byte bytes[] = new byte[size];
                while((read = input.read(bytes)) != -1) 
                    stream.write(bytes, 0, read);
                stream.flush();
                stream.close();

				/*File file = new File(System.getProperty("catalina.base")).getParentFile();*/
				
			}	
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return img;

	}
}
