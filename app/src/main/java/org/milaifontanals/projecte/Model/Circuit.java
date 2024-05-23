package org.milaifontanals.projecte.Model;

import java.util.Date;

public class Circuit {
    public Circuit(int id, Cursa cursa, int num, float distancia, String nom, float preu, Date tempsEstimat) {
        this.id = id;
        this.cursa = cursa;
        this.num = num;
        this.distancia = distancia;
        this.nom = nom;
        this.preu = preu;
        this.tempsEstimat = tempsEstimat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cursa getCursa() {
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    int id; //SON UNICS
    Cursa cursa;
    int num;

    float distancia;
    String nom;
    float preu;
    Date tempsEstimat;

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

    public Date getTempsEstimat() {
        return tempsEstimat;
    }

    public void setTempsEstimat(Date tempsEstimat) {
        this.tempsEstimat = tempsEstimat;
    }
}
