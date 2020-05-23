package com.b2w.starwars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planeta")
public class Planeta {
	
	@Id
	private String  planetaId;
	private String  nomePlaneta;
	private String  climaPlaneta;
	private String  terrenoPlaneta;
	private Integer aparicoesEmFilmes;

	public String getPlanetaId() {
		return planetaId;
	}
	public void setPlanetaId(String planetaId) {
		this.planetaId = planetaId;
	}
	public String getNomePlaneta() {
		return nomePlaneta;
	}
	public void setNomePlaneta(String nomePlaneta) {
		this.nomePlaneta = nomePlaneta;
	}
	public String getClimaPlaneta() {
		return climaPlaneta;
	}
	public void setClimaPlaneta(String climaPlaneta) {
		this.climaPlaneta = climaPlaneta;
	}
	public String getTerrenoPlaneta() {
		return terrenoPlaneta;
	}
	public void setTerrenoPlaneta(String terrenoPlaneta) {
		this.terrenoPlaneta = terrenoPlaneta;
	}
	public Integer getAparicoesEmFilmes() {
		return aparicoesEmFilmes;
	}
	public void setAparicoesEmFilmes(Integer aparicoesEmFilmes) {
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}
}
