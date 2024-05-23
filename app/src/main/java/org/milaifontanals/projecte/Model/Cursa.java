package org.milaifontanals.projecte.Model;

import android.graphics.Bitmap;

import java.util.Date;

public class Cursa {
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

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public String getLloc() {
        return lloc;
    }

    public void setLloc(String lloc) {
        this.lloc = lloc;
    }

    public Esport getEsport() {
        return esport;
    }

    public void setEsport(Esport esport) {
        this.esport = esport;
    }

    public EstatsCursa getEstatCursa() {
        return estatCursa;
    }

    public void setEstatCursa(EstatsCursa estatCursa) {
        this.estatCursa = estatCursa;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLimitInscrits() {
        return limitInscrits;
    }

    public void setLimitInscrits(int limitInscrits) {
        this.limitInscrits = limitInscrits;
    }

    public char getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(char urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    int id;
    String nom;
    Date dataInici;
    Date dataFi;
    String lloc;
    Esport esport;

    EstatsCursa estatCursa;
    String desc;
    int limitInscrits;
    char urlFoto;
    Bitmap foto;
    String web;

    public Cursa(int id, String nom, Date dataInici, Date dataFi, String lloc, Esport esport, EstatsCursa estatCursa, String desc, int limitInscrits, char urlFoto, Bitmap foto, String web) {
        this.id = id;
        this.nom = nom;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.lloc = lloc;
        this.esport = esport;
        this.estatCursa = estatCursa;
        this.desc = desc;
        this.limitInscrits = limitInscrits;
        this.urlFoto = urlFoto;
        this.foto = foto;
        this.web = web;
    }
}
