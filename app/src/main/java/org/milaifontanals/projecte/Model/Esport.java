package org.milaifontanals.projecte.Model;

public class Esport {
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

    int id;
    String nom;

}
