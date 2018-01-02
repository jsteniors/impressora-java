package com.wing.ws.resource;

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
import com.wing.dao.DAO;
import com.wing.model.Categoria;
import com.wing.util.Parameter;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {
	
	private DAO<Categoria> dao;

	@Inject
	public CategoriaResource(DAO<Categoria> dao) {
		this.dao = dao;
	}
	
	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategoria(Categoria cat) {
		Parameter param = new Parameter("pTipoId", cat.getTipo().getId());
		dao.insert(cat);
		List<Categoria> list = dao.listByQuery("catsByTipo", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editCategoria(Categoria cat) {
		Parameter param = new Parameter("pTipoId", cat.getTipo().getId());
		dao.update(cat);
		List<Categoria> list = dao.listByQuery("catsByTipo", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCategoria(Categoria cat) {
		Parameter param = new Parameter("pTipoId", cat.getTipo().getId());
		dao.delete(cat);
		List<Categoria> list = dao.listByQuery("catsByTipo", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{tipoId}")
	public Response listaByMarca(@PathParam("tipoId")Integer id){
		CacheControl cache = new CacheControl();
		cache.setMaxAge(-1);
		Parameter param = new Parameter("pTipoId", id);
		List<Categoria> list = dao.listByQuery("catsByTipo", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).cacheControl(cache).build();
	}

}
