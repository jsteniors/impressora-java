package com.wing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(name="tipoByMarca", query="SELECT tipo FROM Tipo tipo WHERE tipo.marca.nome=:pNome")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tipo {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	
	@ManyToOne
	private Marca marca;
	
	@OneToMany(mappedBy="tipo", cascade= {CascadeType.REMOVE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Categoria> categorias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@JsonIgnore
	public Marca getMarca() {
		return marca;
	}
	@JsonProperty
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	

}
