package com.gestionR.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="AppelsDoffre")
public class AppelsDoffre {
	
	@Id
	private String idoffre;
	
	private String titreOffre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DateDebut;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date DateFin;
	
	
	private List<Besoin> Besoins= null;

	private String etat;
	
	private boolean isTerminated;
	
	private List<Soumission> soumissions ;
	
	
	public List<Soumission> getSoumissions() {
		return soumissions;
	}

	public void setSoumissions(List<Soumission> soumissions) {
		this.soumissions = soumissions;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * @param titreOffre
	 * @param dateDebut
	 * @param dateFin
	 * @param besoins
	 */
	public AppelsDoffre(String titreOffre, Date dateDebut, Date dateFin, List<Besoin> besoins) {
		super();
		this.titreOffre = titreOffre;
		DateDebut = dateDebut;
		DateFin = dateFin;
		Besoins = besoins;
	}

	public String getTitreOffre() {
		return titreOffre;
	}

	public void setTitreOffre(String titreOffre) {
		this.titreOffre = titreOffre;
	}

	/**
	 * 
	 */
	public AppelsDoffre() {
		super();
	}

	/**
	 * @param dateDebut
	 * @param dateFin
	 * @param besoins
	 */
	
	public String getIdoffre() {
		return idoffre;
	}

	public void setIdoffre(String idoffre) {
		this.idoffre = idoffre;
	}

	public Date getDateDebut() {
		return DateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.DateDebut = dateDebut;
	}

	public Date getDateFin() {
		return DateFin;
	}

	public void setDateFin(Date dateFin) {
		this.DateFin = dateFin;
	}

	public List<Besoin> getBesoins() {
		return Besoins;
	}

	public void setBesoins(List<Besoin> besoins) {
		this.Besoins = besoins;
	}

	public boolean getIsTerminated() {
		return isTerminated;
	}

	public void setIsTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
		this.etat="terminé";
	}
	
	
	
	
	
}
