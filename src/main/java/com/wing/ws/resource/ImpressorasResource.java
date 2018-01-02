package com.wing.ws.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.wing.dao.DAO;
import com.wing.model.Impressora;
import com.wing.util.Parameter;

@Path("/impressora")
@Produces(MediaType.APPLICATION_JSON)
public class ImpressorasResource {
	
	private DAO<Impressora> dao;
	
	@Inject
	public ImpressorasResource(DAO<Impressora> dao) {
		this.dao = dao;
	}
	
	@GET
	@Path("/{idImpressora}")
	public Impressora getImpressoraById(@PathParam("idImpressora")Integer id) {
		return dao.findbyId(id);
	}
	
	@PUT
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editImpressora(Impressora imp) {
		Parameter<Integer> param = new Parameter<>("pCatId", imp.getCategoria().getId());
		dao.update(imp);
		List<Impressora> list = dao.listByQuery("impByCategoria", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteImpressora(Impressora imp) {
		Parameter<Integer> param = new Parameter<>("pCatId", imp.getCategoria().getId());
		dao.delete(imp);
		List<Impressora> list = dao.listByQuery("impByCategoria", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/cat/{catId}")
	public Response listImpressoraById(@PathParam("catId")Integer id) {
		CacheControl cache = new CacheControl();
		cache.setMaxAge(-1);
		Parameter<Integer> param = new Parameter<>("pCatId", id);
		List<Impressora> list = dao.listByQuery("impByCategoria", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).cacheControl(cache).build();
	}
	
	
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	
}
