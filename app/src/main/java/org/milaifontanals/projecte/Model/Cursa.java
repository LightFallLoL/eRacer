package org.milaifontanals.projecte.Model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Cursa implements Serializable {

    @SerializedName("cur_id")
    private int id;

    @SerializedName("cur_nom")
    private String nom;

    @SerializedName("cur_data_inici")
    private Date dataInici;

    @SerializedName("cur_data_fi")
    private Date dataFi;

    @SerializedName("cur_lloc")
    private String lloc;

    @SerializedName("esport")
    private Esport esport;

    @SerializedName("estat")
    private EstatsCursa estatCursa;

    @SerializedName("cur_desc")
    private String desc;

    @SerializedName("cur_limit_inscr")
    private int limitInscrits;

    @SerializedName("cur_foto")
    private String urlFoto;

    private Bitmap bitmap; // Este campo no se serializa/deserializa directamente
    @SerializedName("cur_web")
    private String web;

    @SerializedName("circuits")
    private List<Circuit> circuits;

    public List<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }



    public Cursa(int id, String nom, Date dataInici, Date dataFi, String lloc, Esport esport, EstatsCursa estatCursa, String desc, int limitInscrits, String urlFoto, String web) {
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
        this.bitmap = null;
        this.web = web;
    }
}
