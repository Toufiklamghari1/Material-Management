package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Departement")
public class Departement {

	private String id;
	private String nom;
	String chefDepartement;

	public Departement(){

	}

	public Departement(String nom, String chefDepartement) {
		super();
		this.nom = nom;
		this.chefDepartement = chefDepartement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getChefDepartement() {
		return chefDepartement;
	}

	public void setChefDepartement(String chefDepartement) {
		this.chefDepartement = chefDepartement;
	}
}