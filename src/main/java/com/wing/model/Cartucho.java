package com.wing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(name="cartuchosByImp", query="SELECT cart FROM Cartucho cart WHERE cart.impressora.id=:pImpId")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cartucho {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String modelo;
	private String imagem;
	
	@ManyToOne
	private Impressora impressora;
	
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
	public Impressora getImpressora() {
		return impressora;
	}
	@JsonProperty
	public void setImpressora(Impressora impressora) {
		this.impressora = impressora;
	}
	
	

}
