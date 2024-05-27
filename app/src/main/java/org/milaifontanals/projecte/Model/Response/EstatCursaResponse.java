package org.milaifontanals.projecte.Model.Response;


import com.google.gson.annotations.SerializedName;

import org.milaifontanals.projecte.Model.EstatsCursa;

import java.util.List;

public class EstatCursaResponse {
    @SerializedName("estats_cursa")
    private List<EstatsCursa> estatsCursa;

    public List<EstatsCursa> getEstatsCursa() {
        return estatsCursa;
    }

    public void setEstatsCursa(List<EstatsCursa> estatsCursa) {
        this.estatsCursa = estatsCursa;
    }
}
