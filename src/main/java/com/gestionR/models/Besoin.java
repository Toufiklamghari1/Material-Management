package com.gestionR.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Besoins")
public class Besoin  {
    @Id
    private String id;
    private String persID;
    private String type;
    private String discription;

    public Besoin() {
    }

    public String getpersID() {
        return persID;
    }

    public void setpersID(String persID) {
        this.persID = persID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscription() {
        return discription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDicription(String dicription) {
        this.discription = dicription;
    }

    public Besoin(String persID, String type, String dicription) {
        this.persID = persID;
        this.type = type;
        this.discription = dicription;
    }
}