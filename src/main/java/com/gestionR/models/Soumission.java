package com.gestionR.models;


import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "soumission")
public class Soumission {
    @Id
    private String id;

    private String soum;

    private String budget_proposee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_prevue_livraison;

    private String fournisseurAplicants;

    private String idoffre;

    private boolean isAccepted;

    public String getId_soumission() {
        return id;
    }

    public void setId_soumission(String id) {
        this.id = id;
    }

    public String getSoum() {
        return soum;
    }

    public void setSoum(String soum) {
        this.soum = soum;
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

    public String getFournisseurAplicants() {
        return fournisseurAplicants;
    }

    public void setFournisseurAplicants(String fournisseurAplicants) {
        this.fournisseurAplicants = fournisseurAplicants;
    }

    public Soumission(String budget_proposee, Date date_prevue_livraison, String fournisseurAplicants) {
        super();
        this.budget_proposee = budget_proposee;
        this.date_prevue_livraison = date_prevue_livraison;
        this.fournisseurAplicants = fournisseurAplicants;
    }

    public Soumission() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(String idoffre) {
        this.idoffre = idoffre;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}