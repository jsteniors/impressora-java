package com.wing.ws.filter;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wing.util.JPAUtil;

public class InitializationFilter implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent ctx) {
		EntityManager manager = JPAUtil.getEntityManager();
    	ctx.getServletContext().setAttribute("entityManager", manager);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent ctx) {
		EntityManager em = (EntityManager)ctx.getServletContext().getAttribute("entityManager");
    	ctx.getServletContext().removeAttribute("entityManager");
		em.close();
	}
	
	
}
