package com.wing.util;

import com.wing.model.Usuario;

public class LoggedType {
	
	private Usuario user;
	private String token;
	
	public LoggedType(Usuario user) {
		this.user = user;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
