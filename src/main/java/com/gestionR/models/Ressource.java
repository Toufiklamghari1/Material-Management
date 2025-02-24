package com.gestionR.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;


public abstract class Ressource{

	@Id
    private String id;
    private boolean isAffected = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateliv;
    private int garantie;
    private String marque;
    private String fournisseur;

    public Ressource() {
    }

    public Ressource(String id, Date dateliv, int garantie, String marque,String fournisseur) {
        this.id = id;
        this.dateliv = dateliv;
        this.garantie = garantie;
        this.marque = marque;
        this.fournisseur = fournisseur;
    }
    public Ressource(String id) {
    	this.id = id;
    }

	public String getId() {
		return id;
	}
	 public boolean isAffected() {
	        return isAffected;
	    }

	    public void setAffected(boolean affected) {
	        isAffected = affected;
	    }

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateliv() {
        return dateliv;
    }

    public void setDateliv(Date dateliv) {
        this.dateliv = dateliv;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
