package com.wing.util;

import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class JPAUtil {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("impressorasPU");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
    public static EntityManager managerByContext(HttpServletRequest req) {
    	System.out.println("uti  "+req);
    	Enumeration<String> el = req.getServletContext().getAttributeNames(); 
    	while(el.hasMoreElements())
    		System.out.println("________________________"+el.nextElement());
    	return (EntityManager)req.getServletContext().getAttribute("entityManager");
    }
}
