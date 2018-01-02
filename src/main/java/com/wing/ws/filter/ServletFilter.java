package com.wing.ws.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wing.util.LoggedType;

public class ServletFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req  =(HttpServletRequest)request;
		HttpServletResponse resp  =(HttpServletResponse)response;
		HttpSession session = req.getSession(true);
		String uri = req.getRequestURI();
		
		String method = req.getMethod().toLowerCase();
		System.out.println("filtrando "+method);
		
		if(method.equals("get")) {
			chain.doFilter(request, response);
		}else{
			if(session.getAttribute("userLogged")!=null) {
			LoggedType log = (LoggedType)session.getAttribute("userLogged");
			System.out.println("user: "+log.getToken());
				if(log!=null) {
					chain.doFilter(request, response);
				}else resp.sendError(404, "Usuario nao logado");
			}else resp.sendError(404, "Usuario nao logados"); 
		}
		System.out.println("passou");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
