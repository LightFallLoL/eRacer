package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Inscripcio {
    @SerializedName("ins_id")
    private int insId;

    @SerializedName("ins_par_id")
    private int parId;

    @SerializedName("ins_data")
    private String insData;

    @SerializedName("ins_dorsal")
    private int dorsal;

    @SerializedName("ins_retirat")
    private Boolean retirat;

    @SerializedName("ins_bea_id")
    private Integer beaId;

    @SerializedName("ins_ccc_id")
    private int categoriaCircuitId;



    public Inscripcio() {
    }

    public Inscripcio(int insId, int parId, String insData, int dorsal, Boolean retirat, Integer beaId, int cccId, int checkpoints, Participant participant) {
        this.insId = insId;
        this.parId = parId;
        this.insData = insData;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.beaId = beaId;
        this.categoriaCircuitId = cccId;

       // this.participant = participant;
    }
    public Inscripcio(int parId, String insData, int dorsal, Boolean retirat, Integer beaId, int cccId, int checkpoints, Participant participant) {
        this.parId = parId;
        this.insData = insData;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.beaId = beaId;
        this.categoriaCircuitId = cccId;
       // this.participant = participant;
    }

    public Inscripcio(int parId, String insData, int dorsal, Boolean retirat, Integer beaId, int categoriaCircuitId) {
        this.insId = insId;
        this.parId = parId;
        this.insData = insData;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.beaId = beaId;
        this.categoriaCircuitId = categoriaCircuitId;
    }

    //public Participant getParticipant() {    return participant;    }

   // public void setParticipant(Participant participant) {        this.participant = participant;    }

    public Inscripcio(int insId, int parId, String insData, int dorsal, Boolean retirat, Integer beaId, int cccId, int checkpoints) {
        this.insId = insId;
        this.parId = parId;
        this.insData = insData;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.beaId = beaId;
        this.categoriaCircuitId = cccId;
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

    public String getInsData() {
        return insData;
    }

    public void setInsData(String insData) {
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

    public int getcategoriaCircuitId() {
        return categoriaCircuitId;
    }

    public void setCategoriaCircuitId(int cccId) {
        this.categoriaCircuitId = cccId;
    }


}
