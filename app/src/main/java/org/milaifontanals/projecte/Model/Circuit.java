package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Circuit implements Serializable {
    @SerializedName("cir_id")
    private int id;

    @SerializedName("cir_cur_id")
    private int curId;

    @SerializedName("cir_num")
    private int num;

    @SerializedName("cir_distancia")
    private float distancia;

    @SerializedName("cir_nom")
    private String nom;

    @SerializedName("cir_preu")
    private float preu;

    @SerializedName("cir_temps_estimat")
    private int tempsEstimat;

    @SerializedName("cir_checkpoints")
    private int checkpoints;

    @SerializedName("categories")
    private List<Categoria> categories;


    public Circuit(int id, int curId, int num, float distancia, String nom, float preu, int tempsEstimat, int checkpoints, List<Categoria> categories) {
        this.id = id;
        this.curId = curId;
        this.num = num;
        this.distancia = distancia;
        this.nom = nom;
        this.preu = preu;
        this.tempsEstimat = tempsEstimat;
        this.checkpoints = checkpoints;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int curId) {
        this.curId = curId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public int getTempsEstimat() {
        return tempsEstimat;
    }

    public void setTempsEstimat(int tempsEstimat) {
        this.tempsEstimat = tempsEstimat;
    }

    public int getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(int checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<Categoria> getCategories() {
        return categories;
    }

    public void setCategories(List<Categoria> categories) {
        this.categories = categories;
    }

    public String getFormattedTempsEstimat() {
        int hours = tempsEstimat / 60;
        int minutes = tempsEstimat % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
