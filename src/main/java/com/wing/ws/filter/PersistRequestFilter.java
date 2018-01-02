package com.wing.ws.filter;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class PersistRequestFilter implements ContainerRequestFilter{
	
	//@Inject PersistService ps;
	
	@Context HttpServletRequest req;
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		EntityManager em = (EntityManager)req.getServletContext().getAttribute("entityManager");
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		
		req.getServletContext().setAttribute("entityManager", em);
		
		return request;
	}
	
	
}
