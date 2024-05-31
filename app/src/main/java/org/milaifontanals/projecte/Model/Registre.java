package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Registre {
    @SerializedName("reg_id")
    private int regId;

    @SerializedName("reg_ins_id")
    private int regInsId;

    @SerializedName("reg_chk_id")
    private int regChkId;

    @SerializedName("reg_temps")
    private Date regTemps;

    @SerializedName("inscripcio")
    private Inscripcio inscripcio;

    @SerializedName("checkpoint")
    private Checkpoint checkpoint;

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public int getRegInsId() {
        return regInsId;
    }

    public void setRegInsId(int regInsId) {
        this.regInsId = regInsId;
    }

    public int getRegChkId() {
        return regChkId;
    }

    public void setRegChkId(int regChkId) {
        this.regChkId = regChkId;
    }

    public Date getRegTemps() {
        return regTemps;
    }

    public void setRegTemps(Date regTemps) {
        this.regTemps = regTemps;
    }

    public Inscripcio getInscripcio() {
        return inscripcio;
    }

    public void setInscripcio(Inscripcio inscripcio) {
        this.inscripcio = inscripcio;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }
}
