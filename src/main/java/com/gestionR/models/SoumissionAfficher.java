package com.gestionR.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class SoumissionAfficher {
	

	
	
	private String id;
	
	private String budget_proposee;
	
	private Date date_prevue_livraison;
	
	private String nomFournisseur;
	
	private String gerant;
	
	private String lieuSociete;
	
	private String nomSociete;
	
	private String idFournisseur;
	
	private boolean isblocked;
	
	private boolean isSubmitted;
	
	
	public boolean isSubmitted() {
		return isSubmitted;
	}

	public void setSubmitted(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public boolean isIsblocked() {
		return isblocked;
	}

	public void setIsblocked(boolean isblocked) {
		this.isblocked = isblocked;
	}

	

	public SoumissionAfficher(String budget_proposee, Date date_prevue_livraison, String nomFournisseur, String gerant,
			String lieuSociete, String nomSociete) {
		super();
		this.budget_proposee = budget_proposee;
		this.date_prevue_livraison = date_prevue_livraison;
		this.nomFournisseur = nomFournisseur;
		this.gerant = gerant;
		this.lieuSociete = lieuSociete;
		this.nomSociete = nomSociete;
	}

	public SoumissionAfficher() {
		super();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBudget_proposee() {
		return budget_proposee;
	}

	public void setBudget_proposee(String budget_proposee) {
		this.budget_proposee = budget_proposee;
	}

	public Date getDate_prevue_livraison() {
		return date_prevue_livraison;
	}

	public void setDate_prevue_livraison(Date date_prevue_livraison) {
		this.date_prevue_livraison = date_prevue_livraison;
	}

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
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

	public String getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(String idFournisseur) {
		this.idFournisseur = idFournisseur;
	}
	
	

	
	

}
