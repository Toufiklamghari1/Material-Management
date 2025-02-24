package com.gestionR.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Pannes")
public class Panne {
	@Id
	private String idPanne;
	private String idRessource;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateApparition;

	private String explication;

	private FrequencePanne frequence;

	private  OrdreLogiciel ordreLogiciel;

	private User personne;
	/*
	@DBRef
	private Ressource ressource;
	*/

	public Object getPersonne() {
		return personne;
	}

	public void setPersonne(User user) {
		this.personne = user;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
	}

	public FrequencePanne getFrequence() {
		return frequence;
	}

	public void setFrequence(FrequencePanne frequence) {
		this.frequence = frequence;
	}

	public OrdreLogiciel getOrdreLogiciel() {
		return ordreLogiciel;
	}

	public void setOrdreLogiciel(OrdreLogiciel ordreLogiciel) {
		this.ordreLogiciel = ordreLogiciel;
	}

	public Panne() {
		super();
	}

	public Panne(Date dateApparition, String explication, FrequencePanne frequence, OrdreLogiciel ordreLogiciel) {
		super();
		this.dateApparition = dateApparition;
		this.explication = explication;
		this.frequence = frequence;
		this.ordreLogiciel = ordreLogiciel;
	}

	public String getIdPanne() {
		return idPanne;
	}

	public void setIdPanne(String idPanne) {
		this.idPanne = idPanne;
	}

	public String getIdRessource() {
		return idRessource;
	}

	public void setIdRessource(String idRessource) {
		this.idRessource = idRessource;
	}

	public Date getDateApparition() {
		return dateApparition;
	}

	public void setDateApparition(Date dateApparition) {
		this.dateApparition = dateApparition;
	}
}