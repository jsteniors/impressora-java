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
@NamedQuery(name="impByCategoria", query="SELECT imp FROM Impressora imp WHERE imp.categoria.id=:pCatId")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Impressora {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String modelo;
	
	private String imagem;
	
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany(mappedBy="impressora", cascade= {CascadeType.REMOVE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Cartucho> cartuchos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	@JsonIgnore
	public Categoria getCategoria() {
		return categoria;
	}
	@JsonProperty
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Cartucho> getCartuchos() {
		return cartuchos;
	}

	public void setCartuchos(List<Cartucho> cartuchos) {
		this.cartuchos = cartuchos;
	}
	
}
