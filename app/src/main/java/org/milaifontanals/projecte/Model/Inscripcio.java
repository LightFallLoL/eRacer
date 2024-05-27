package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Inscripcio {
    @SerializedName("ins_id")
    private int insId;

    @SerializedName("ins_par_id")
    private int parId;

    @SerializedName("ins_data")
    private Date insData;

    @SerializedName("ins_dorsal")
    private int dorsal;

    @SerializedName("ins_retirat")
    private Boolean retirat;

    @SerializedName("ins_bea_id")
    private Integer beaId;

    @SerializedName("ins_ccc_id")
    private int cccId;

    @SerializedName("ins_checkpoints")
    private int checkpoints;


    public Inscripcio(int insId, int parId, Date insData, int dorsal, Boolean retirat, Integer beaId, int cccId, int checkpoints) {
        this.insId = insId;
        this.parId = parId;
        this.insData = insData;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.beaId = beaId;
        this.cccId = cccId;
        this.checkpoints = checkpoints;
    }

    public int getInsId() {

        return insId;
    }

    public void setInsId(int insId) {
        this.insId = insId;
    }

    public int getParId() {
        return parId;
    }

    public void setParId(int parId) {
        this.parId = parId;
    }

    public Date getInsData() {
        return insData;
    }

    public void setInsData(Date insData) {
        this.insData = insData;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public Boolean getRetirat() {
        return retirat;
    }

    public void setRetirat(Boolean retirat) {
        this.retirat = retirat;
    }

    public Integer getBeaId() {
        return beaId;
    }

    public void setBeaId(Integer beaId) {
        this.beaId = beaId;
    }

    public int getCccId() {
        return cccId;
    }

    public void setCccId(int cccId) {
        this.cccId = cccId;
    }

    public int getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(int checkpoints) {
        this.checkpoints = checkpoints;
    }
}
