package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "Ordinateur")
public class Ordinateure extends Ressource {
    private String cpu;
    private int ram;
    private int rom;
    private String ecran;

    public Ordinateure() {
        super();
    }

    public Ordinateure(String id, Date dateliv, int garantie, String marque, String cpu, int ram, int rom, String ecran,String fournisseur) {
        super(id, dateliv, garantie, marque, fournisseur);
        this.cpu = cpu;
        this.ram = ram;
        this.rom = rom;
        this.ecran = ecran;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public String getEcran() {
        return ecran;
    }

    public void setEcran(String ecran) {
        this.ecran = ecran;
    }
}