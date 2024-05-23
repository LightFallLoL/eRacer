package org.milaifontanals.projecte.Model;

public class EstatsCursa {
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

    int id;
    String nom;
}
