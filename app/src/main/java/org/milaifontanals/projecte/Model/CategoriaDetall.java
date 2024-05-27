package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

public class CategoriaDetall {

    @SerializedName("cat_id")
    private int catId;

    @SerializedName("cat_esp_id")
    private int espId;

    @SerializedName("cat_nom")
    private String catNom;

    public CategoriaDetall(int catId, int espId, String catNom) {
        this.catId = catId;
        this.espId = espId;
        this.catNom = catNom;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getEspId() {
        return espId;
    }

    public void setEspId(int espId) {
        this.espId = espId;
    }

    public String getCatNom() {
        return catNom;
    }

    public void setCatNom(String catNom) {
        this.catNom = catNom;
    }
}
