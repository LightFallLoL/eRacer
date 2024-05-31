package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;
/**
 * Classe Participant amb les anotacions pertintents
 */
public class Participant {
    @SerializedName("par_id")
    private int id;

    @SerializedName("par_nif")
    private String nif;

    @SerializedName("par_nom")
    private String nom;

    @SerializedName("par_cognoms")
    private String cognoms;

    @SerializedName("par_data_naixement")
    private String dataNaixement;

    @SerializedName("par_telefon")
    private String telefon;

    @SerializedName("par_email")
    private String email;

    @SerializedName("par_es_federat")
    private int esFederat;

    public Participant(int id, String nif, String nom, String cognoms, String dataNaixement, String telefon, String email, int esFederat) {
        this.id = id;
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.telefon = telefon;
        this.email = email;
        this.esFederat = esFederat;
    }

    public Participant(String nif, String nom, String cognoms, String dataNaixement, String telefon, String email, int esFederat) {
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.telefon = telefon;
        this.email = email;
        this.esFederat = esFederat;
    }

    public Participant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEsFederat() {
        return esFederat;
    }

    public void setEsFederat(int esFederat) {
        this.esFederat = esFederat;
    }
}