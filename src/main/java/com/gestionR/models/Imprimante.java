package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "Imprimante")
public class Imprimante extends Ressource{
    private int resolution;
    private int vetesseImpristion;

    public Imprimante() {
        super();
    }

    public Imprimante(String id, Date dateliv, int garantie, String marque, int resolution, int vetesseImpristion ,String fournisseur) {
        super(id, dateliv, garantie, marque, fournisseur);
        this.resolution = resolution;
        this.vetesseImpristion = vetesseImpristion;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getVetesseImpristion() {
        return vetesseImpristion;
    }

    public void setVetesseImpristion(int vetesseImpristion) {
        this.vetesseImpristion = vetesseImpristion;
    }
}
