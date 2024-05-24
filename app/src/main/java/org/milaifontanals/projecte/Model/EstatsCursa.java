package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

public class EstatsCursa {
    @SerializedName("est_id")
    private int id;

    @SerializedName("est_nom")
    private String nom;
    public EstatsCursa(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public EstatsCursa(int id) {
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
