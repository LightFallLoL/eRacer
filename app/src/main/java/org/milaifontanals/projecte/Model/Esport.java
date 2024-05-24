package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

public class Esport {


    @SerializedName("esp_id")
    private int id;

    @SerializedName("esp_nom")
    private String nom;
    public Esport(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Esport(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}
