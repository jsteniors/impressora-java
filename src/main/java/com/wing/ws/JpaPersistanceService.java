package com.wing.ws;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.Provider;

public class JpaPersistanceService implements Provider<EntityManager> {
	
	private EntityManagerFactory emf;
	
	public JpaPersistanceService(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public EntityManager get() {
		return emf.createEntityManager();
	}
	

}
