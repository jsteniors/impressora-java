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
@NamedQuery(name="catsByTipo", query="SELECT cat FROM Categoria cat WHERE cat.tipo.id=:pTipoId")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categoria {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	
	@ManyToOne
	private Tipo tipo;
	
	@OneToMany(mappedBy="categoria", cascade= {CascadeType.REMOVE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Impressora> impressoras;

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
	public Tipo getTipo() {
		return tipo;
	}
	@JsonProperty
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Impressora> getImpressoras() {
		return impressoras;
	}

	public void setImpressoras(List<Impressora> impressoras) {
		this.impressoras = impressoras;
	}

	
}
