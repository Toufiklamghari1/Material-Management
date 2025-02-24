package com.gestionR.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Fournisseur")
public class Fournisseur extends User {
	private String gerant;
	private String lieuSociete;
	private String nomSociete;
	private String idFourniseur;
	public boolean isblocked;
	public String motifOfblocking;

	public String getMotifOfblocking() {
		return motifOfblocking;
	}

	public void setMotifOfblocking(String motifOfblocking) {
		this.motifOfblocking = motifOfblocking;
	}

	public boolean isIsblocked() {
		return isblocked;
	}

	public void setIsblocked(boolean isblocked) {
		this.isblocked = isblocked;
	}

	public Fournisseur(String username , String email , String password){
		super(username, email, password);
		idFourniseur=super.getId();

	}

	public Fournisseur(String username , String email , String password ,String gerant, String lieuSociete, String nomSociete) {
		super(username, email, password);
		this.gerant = gerant;
		this.lieuSociete = lieuSociete;
		this.nomSociete = nomSociete;
		idFourniseur=super.getId();
	}

	public Fournisseur() {
		super();
		idFourniseur=super.getId();

	}

	public String getGerant() {
		return gerant;
	}

	public void setGerant(String gerant) {
		this.gerant = gerant;
	}

	public String getLieuSociete() {
		return lieuSociete;
	}

	public void setLieuSociete(String lieuSociete) {
		this.lieuSociete = lieuSociete;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getIdFourniseur() {
		return idFourniseur;
	}

	public void setIdFourniseur(String idFourniseur) {
		this.idFourniseur = idFourniseur;
	}
}