package com.wing.ws.filter;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.core.ExtendedUriInfo;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.wing.model.Role;
import com.wing.util.LoggedType;

@Provider
public class AuthResponseFilter implements ContainerResponseFilter{
	

	@Context
	HttpServletRequest req;
	@Context ExtendedUriInfo info;
	
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		
		if(!isAuthenticated(response))
			response.setResponse(Response.status(Response.Status.UNAUTHORIZED).build());
		return response;
	}
	
	public boolean isAuthenticated(ContainerResponse res) {
		HttpSession session = req.getSession(true);
		List<Role> userRoles = null;
		boolean logs = false;
		if(session.getAttribute("userLogged")!=null) {
			LoggedType log = (LoggedType) session.getAttribute("userLogged");
			if(log.getUser()!=null) {
				if(log.getUser().getRoles()!=null) {
//					System.out.println("roles h");
					userRoles = log.getUser().getRoles();
					logs = true;
				}
			}
		}
		
		/*EntityManager em = (EntityManager)req.getServletContext().getAttribute("entityManager");
		if(em.getTransaction().isActive())
			em.getTransaction().commit();
		req.getServletContext().setAttribute("entityManager", em);*/
		
		if(info.getMatchedMethod().isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed roles = info.getMatchedMethod().getAnnotation(RolesAllowed.class);
			for(String rol:roles.value()) {
				if(logs) {
					for(Role role:userRoles) {
//						System.out.println(role.getRole()+" eq "+rol);
						if(role.getRole().toLowerCase().trim().equals(rol.toLowerCase().trim())) {
								return true;
						}
					}
				}
			}
		} else if(info.getMatchedMethod().isAnnotationPresent(PermitAll.class)) return true;
			else return req.getMethod().toLowerCase().equals("get");
		return false;
	}

}
