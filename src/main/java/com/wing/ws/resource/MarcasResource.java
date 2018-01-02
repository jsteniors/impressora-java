package com.wing.ws.resource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.wing.dao.DAO;
import com.wing.model.Cartucho;
import com.wing.model.Categoria;
import com.wing.model.Impressora;
import com.wing.model.Marca;
import com.wing.model.Tipo;
import com.wing.util.Parameter;


@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
public class MarcasResource {
	
	private DAO<Marca> dao;
	
	@Inject
	public MarcasResource(DAO<Marca> dao) {
		this.dao = dao;
	}
	
	@GET
	public Response  getMarcas(){
		List<Marca> list = dao.listAll();
		/*if(list.isEmpty())
			return Response.status(Response.Status.NOT_FOUND).build();*/
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{name}")
	public Response getByName(@PathParam("name") String name) {
		CacheControl cache = new CacheControl();
		Parameter parameter = new Parameter("pNome",name);
		Marca marca = dao.findByQuery("marcaByName", Arrays.asList(parameter));
		if(marca==null)
			return Response.status(Response.Status.NOT_FOUND).build();
		else
			return Response.ok(marca, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMarca(Marca marca) {
		if(marca.getTipos()!=null) {
			for(Tipo tipo:marca.getTipos()) {
				tipo.setMarca(marca);
				if(tipo.getCategorias()!=null) {
					for(Categoria cat:tipo.getCategorias()){
						cat.setTipo(tipo);
						if(cat.getImpressoras()!=null) {
							for(Impressora imp:cat.getImpressoras()) {
								imp.setCategoria(cat);
								if(imp.getCartuchos()!=null) {
									for(Cartucho car:imp.getCartuchos()) {
										car.setImpressora(imp);
									}
								}
							}
						}
					}
				}
			}
		}
		
		dao.insert(marca);
		return Response.ok(dao.listAll(), MediaType.APPLICATION_JSON).build();
	}
	
	
}
