package com.example.microbsentryapp.model;

public class Zaposleni {

    private String name;
    private String surname;
    private String id;
    private String city;
    private String storageUnits;

    public Zaposleni() {
    }

    public Zaposleni(String name) {
        this.name = name;
    }

    public Zaposleni(String name, String surname, String id, String city, String storageUnits) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.city = city;
        this.storageUnits = storageUnits;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStorageUnits() {
        return storageUnits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStorageUnits(String storageUnits) {
        this.storageUnits = storageUnits;
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }
}
