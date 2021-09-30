package com.example.microbsentryapp.model;

public class Kupac {

    private String name;
    private String pib;
    private String id;
    private String zaposleniID;

    public Kupac(String name, String pib, String id, String zaposleniID) {
        this.name = name;
        this.pib = pib;
        this.id = id;
        this.zaposleniID = zaposleniID;
    }

    public String getName() {
        return name;
    }

    public String getPib() {
        return pib;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZaposleniID(String zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getZaposleniID() {
        return zaposleniID;
    }
}
