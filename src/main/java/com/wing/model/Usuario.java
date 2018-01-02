package com.wing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(name="userByLogin", query="SELECT user FROM Usuario user WHERE user.login=:pLogin AND user.senha=:pSenha")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(unique=true)
	private String login;
	private String senha;
	private String nome;
	
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@ManyToMany(mappedBy="users", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Role> roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
