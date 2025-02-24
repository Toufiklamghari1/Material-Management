package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Document(collection = "Affectation")
public class Affectation {

    private String persID;
    private String ressourceNum;
    private String dateAffectaion;
    private boolean estPartager;

    public Affectation(String persID, String ressourceNum ,boolean estPartager) {
        LocalDate date = LocalDate.now();
        this.persID = persID;
        this.ressourceNum = ressourceNum;
        this.dateAffectaion = date+"";
        this.estPartager = estPartager;
    }

    public String getPersID() {
        return persID;
    }

    public void setPersID(String persID) {
        this.persID = persID;
    }

    public String getRessourceId() {
        return ressourceNum;
    }

    public void setRessourceId(Ressource ressource) {
        this.ressourceNum = ressourceNum;
    }

    public String getDateAffectaion() {
        return dateAffectaion;
    }

    public void setDateAffectaion(LocalDate dateAffectaion) {
        this.dateAffectaion = dateAffectaion+"";
    }

    public boolean isEstPartager() {
        return estPartager;
    }

    public void setEstPartager(boolean estPartager) {
        this.estPartager = estPartager;
    }
}
