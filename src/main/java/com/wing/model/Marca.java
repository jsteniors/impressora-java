package com.wing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@NamedQuery(name="marcaByName", query="SELECT marca FROM Marca marca WHERE marca.nome=:pNome")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Marca {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(unique=true)
	private String nome;
	
	private String imagem;
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OneToMany(mappedBy="marca", cascade= {CascadeType.REMOVE, CascadeType.PERSIST} ,fetch=FetchType.EAGER)
	private List<Tipo> tipos;
	
	

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
	

}
