	package com.wing.ws.resource;

import java.util.Arrays;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.wing.dao.DAO;
import com.wing.dao.TesteDAO;
import com.wing.model.Usuario;
import com.wing.util.CryptManager;
import com.wing.util.LoggedType;
import com.wing.util.Parameter;

@Path("/auth")
public class AuthResource {
	
	private DAO<Usuario> dao;
	
	@Context
	private HttpServletRequest req;
	
	@Inject
	public AuthResource(DAO<Usuario> dao) {
		this.dao = dao;
	}


	@GET
	@Path("/{login}/{senha}")
	public Response logar(@PathParam("login")String plogin,@PathParam("senha") String psenha) {
		HttpSession session = req.getSession(true);
		System.out.println("logando");
		String login = plogin;
		String senha = psenha;
		Parameter param = new Parameter<>("pLogin", login);
		Parameter param1 = new Parameter<>("pSenha", CryptManager.encrypt(senha));
		Usuario user = dao.findByQuery("userByLogin", Arrays.asList(param,param1));
		if(user!=null) {
			System.out.println("logou");
			LoggedType log = new LoggedType(user);
			log.setToken(session.getId());
			session.setAttribute("userLogged", log);
			
			return Response.ok(log, MediaType.APPLICATION_JSON).build();
		}else return Response.status(404).build();
			
	}
	
	
	@GET
	@Path("/user")
	public Response getLogged() {
		HttpSession session = req.getSession(true);
		Object user = session.getAttribute("userLogged");
		if(user!=null)
			return Response.ok(user, MediaType.APPLICATION_JSON).build();
		else return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	/*@GET
	@Path("/first")
	@RolesAllowed("USER")
	@Transactional
	public Response getFirst() {
		//System.out.println(tdao.getEntityManager());
		return Response.ok(dao.lists(), MediaType.APPLICATION_JSON).build();
	}*/
	
	@GET
	@Path("/logout")
	public Response deslogar() {
		ServletContext context = req.getServletContext();
		context.removeAttribute("logged");
		HttpSession session = req.getSession();
		session.invalidate();
		return Response.ok().build();
	}
	
	/*@GET
	@Path("/robot")
	public Response getDado() {
		Random rand = new Random();
		InputStream stream = getClass().getResourceAsStream("../robot/messages.properties");
		Properties prop = new Properties();
		try {
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = "{";
		int num = rand.nextInt(4);
		response.concat("message: "+prop.getProperty("message["+num +"]"));
		response.concat("}");
		return Response.ok(response, MediaType.APPLICATION_JSON).build();
	}*/
	
	
	
}
