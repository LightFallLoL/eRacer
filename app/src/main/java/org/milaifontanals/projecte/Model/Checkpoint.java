package org.milaifontanals.projecte.Model;

import com.google.gson.annotations.SerializedName;

public class Checkpoint {
    @SerializedName("chk_id")
    private int chkId;
    @SerializedName("chk_pk")
    private int chkPk;
    @SerializedName("chk_cir_id")
    private int chkCirId;

    public int getChkId() {
        return chkId;
    }

    public void setChkId(int chkId) {
        this.chkId = chkId;
    }

    public int getChkPk() {
        return chkPk;
    }

    public void setChkPk(int chkPk) {
        this.chkPk = chkPk;
    }

    public int getChkCirId() {
        return chkCirId;
    }

    public void setChkCirId(int chkCirId) {
        this.chkCirId = chkCirId;
    }
}
