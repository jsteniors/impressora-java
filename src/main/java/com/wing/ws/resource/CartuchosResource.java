package com.wing.ws.resource;

import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.wing.dao.DAO;
import com.wing.model.Cartucho;
import com.wing.model.Impressora;
import com.wing.util.Parameter;

@Path("/cartuchos")
@Produces(MediaType.APPLICATION_JSON)
public class CartuchosResource {
	
	private DAO<Cartucho> dao;
	
	@Inject
	public CartuchosResource(DAO<Cartucho> dao) {
		this.dao = dao;
	}

	@GET
	@Path("/{impId}")
	public Response listCartuchosByImpressora(@PathParam("impId") Integer id){
		CacheControl cache = new CacheControl();
		cache.setMaxAge(-1);
		Parameter<Integer> param = new Parameter<>("pImpId", id);
		List<Cartucho> list = dao.listByQuery("cartuchosByImp", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).cacheControl(cache).build();
	}
	
	
	
	@PUT
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editCartucho(Cartucho cart) {
		Parameter<Integer> param = new Parameter<>("pImpId", cart.getImpressora().getId());
		dao.update(cart);
		List<Cartucho> list = dao.listByQuery("cartuchosByImp", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCartucho(Cartucho cart) {
		Parameter<Integer> param = new Parameter<>("pImpId", cart.getImpressora().getId());
		dao.delete(cart);
		List<Cartucho> list = dao.listByQuery("cartuchosByImp", Arrays.asList(param));
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
}
