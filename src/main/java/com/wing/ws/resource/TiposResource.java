package com.wing.ws.resource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.wing.dao.DAO;
import com.wing.model.Marca;
import com.wing.model.Tipo;
import com.wing.util.Parameter;

@Path("/tipos")
@Produces(MediaType.APPLICATION_JSON)
public class TiposResource {
	
	private DAO<Tipo> dao;
	@Context
	private HttpServletRequest req;
	
	@Inject
	public TiposResource(DAO<Tipo> dao) {
		this.dao = dao;
	}

	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTipo(Tipo tipo) {
		List<Tipo> list = null;
		if(tipo!=null) {
			Marca marca = tipo.getMarca();
			if(marca!=null) {
				String nome = marca.getNome();
				if(nome!=null) {
					Parameter<String> param = new Parameter<>("pNome", nome);
					Marca find = new DAO<Marca>(Marca.class).findByQuery("marcaByName", Arrays.asList(param));
					tipo.setMarca(find);
				}
			}
		}
		
		dao.insert(tipo);
		list = listByQuery("pNome", tipo.getMarca().getNome());
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editTipo(Tipo tipo) {
		List<Tipo> list = null;
		if(tipo!=null) {
			Marca marca = tipo.getMarca();
			if(marca!=null) {
				String nome = marca.getNome();
				if(nome!=null) {
					Parameter<String> param = new Parameter<>("pNome", nome);
					Marca find = new DAO<Marca>(Marca.class).findByQuery("marcaByName", Arrays.asList(param));
					tipo.setMarca(find);
				}
			}
			dao.update(tipo);
			list = listByQuery("pNome", marca.getNome());
		}
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTipo(Tipo tipo) {
		dao.delete(tipo);		
		List list = listByQuery("pNome", tipo.getMarca().getNome());
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{marcaNome}")
	public Response listTiposByMarca(@PathParam("marcaNome") String marca){
		CacheControl cache = new CacheControl();
		cache.setMaxAge(-1);
		List<Tipo> list = listByQuery("pNome", marca);
		
		if(list==null) return Response.status(Response.Status.NOT_FOUND).build();
		else
			if(list.isEmpty()) 
				return Response.status(Response.Status.NOT_FOUND).build();
			else
				return Response.ok(list, MediaType.APPLICATION_JSON).cacheControl(cache).build();
	}

	private List listByQuery(String name, String param) {
		Parameter<String> parameter = new Parameter<>(name, param);
		List<Tipo> list = dao.listByQuery("tipoByMarca", Arrays.asList(parameter));
		return list;
	}

}
