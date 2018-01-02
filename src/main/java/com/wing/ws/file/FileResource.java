package com.wing.ws.file;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wing.dao.TesteDAO;

@Path("/upload")
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {
	
	
	private TesteDAO dao;

//	@Inject
	public FileResource(/*TesteDAO dao*/) {
//		this.dao = dao;
	}


	/*@GET
	public List<Usuario> getMethod() {
		System.out.println("em: "+dao.getEntityManager());
		return new DAO<Usuario>(Usuario.class).listAll();
	}*/
	
	/*@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		if(imp!=null)
			System.out.println(imp.getModelo());
		else System.out.println("sem impressora");
//		String uploadedFileLocation = "d://uploaded/" + fileDetail.getFileName();
		String uploadedFileLocation = this.getClass().getResource(".").toString()+"/"+fileDetail.getFileName();

		// save it
		//writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;
		System.out.println("saida: "+output);
//		return Response.status(200).entity(output).build();

	}*/
}
