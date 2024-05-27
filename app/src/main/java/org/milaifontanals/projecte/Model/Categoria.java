package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categoria {
    @SerializedName("ccc_id")
    private int cccId;

    @SerializedName("ccc_cat_id")
    private int catId;

    @SerializedName("ccc_cir_id")
    private int cirId;

    @SerializedName("categoria")
    private CategoriaDetall categoria;

    @SerializedName("inscripcions")
    private List<Inscripcio> inscripcions;


    public Categoria(int cccId, int catId, int cirId, CategoriaDetall categoria, List<Inscripcio> inscripcions) {
        this.cccId = cccId;
        this.catId = catId;
        this.cirId = cirId;
        this.categoria = categoria;
        this.inscripcions = inscripcions;
    }

    public int getCccId() {
        return cccId;
    }

    public void setCccId(int cccId) {
        this.cccId = cccId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getCirId() {
        return cirId;
    }

    public void setCirId(int cirId) {
        this.cirId = cirId;
    }

    public CategoriaDetall getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDetall categoria) {
        this.categoria = categoria;
    }

    public List<Inscripcio> getInscripcions() {
        return inscripcions;
    }

    public void setInscripcions(List<Inscripcio> inscripcions) {
        this.inscripcions = inscripcions;
    }
}
