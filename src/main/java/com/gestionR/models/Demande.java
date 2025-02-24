package com.gestionR.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Demandes")
public class Demande {

    @Id
    private String id;
    private String num;
    private String chef;
    private String date;
    private DemandeState state = DemandeState.STATE_OFFRE;
    private List besoins;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Demande(String chef, List besoins) {
        Date date = new Date();
        this.date = date.toString();
        this.chef = chef;
        this.besoins = besoins;
        this.num = date.getYear()+""+date.getTime()+""+besoins.size();
    }

    public DemandeState getState() {
        return state;
    }

    public void setState(DemandeState state) {
        this.state = state;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getBesoins() {
        return besoins;
    }

    public void setBesoins(List besoins) {
        this.besoins = besoins;
    }
}
