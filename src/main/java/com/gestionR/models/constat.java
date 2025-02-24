package com.gestionR.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="constat")
public class constat {
	@Id
	private String idconstat;
	private String ordrePanne;
	private String code_ressource;
	private String description;
	private String idTech;
	public constat(){

	}

	public String getIdconstat() {
		return idconstat;
	}

	public void setIdconstat(String idconstat) {
		this.idconstat = idconstat;
	}

	public String getCode_ressource() {
		return code_ressource;
	}

	public String getOrdrePanne() {
		return ordrePanne;
	}

	public void setOrdrePanne(String ordrePanne) {
		this.ordrePanne = ordrePanne;
	}

	public void setCode_ressource(String code_ressource) {
		this.code_ressource = code_ressource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdTech() {
		return idTech;
	}

	public void setIdTech(String idTech) {
		this.idTech = idTech;
	}
}