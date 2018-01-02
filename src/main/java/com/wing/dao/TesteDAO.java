package com.wing.dao;

import javax.persistence.EntityManager;

import com.wing.model.Usuario;

public class TesteDAO {
	
	
	private EntityManager emr;
	
	public TesteDAO(EntityManager emr) {
		this.emr = emr;
	}
	
	public EntityManager getEntityManager() {
		Usuario find = emr.find(Usuario.class, 1);
		System.out.println("emr: "+find.getNome());
		return emr;
	}
	
	
}
