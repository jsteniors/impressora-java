package com.wing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String role;
	
	@ManyToMany(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	private List<Usuario> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@JsonIgnore
	public List<Usuario> getUsers() {
		return users;
	}
	@JsonProperty
	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
	
	@Override
	public boolean equals(Object obj) {
		System.out.println("compare");
		if(obj!=null) {
			System.out.println(obj.getClass());
			Role rol = (Role)obj;
			return this.getRole().toLowerCase().trim().equals(rol.getRole().toLowerCase().trim());
		}else return false;
	}
	
}
