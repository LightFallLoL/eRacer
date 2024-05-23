package org.milaifontanals.projecte.Model;

import java.util.Date;

public class Inscripcio {
    public Inscripcio(int id, int participantId, Date data, int dorsal, boolean retirat, int bea_id, int circuitsCatId) {
        this.id = id;
        this.participantId = participantId;
        this.data = data;
        this.dorsal = dorsal;
        this.retirat = retirat;
        this.bea_id = bea_id;
        this.circuitsCatId = circuitsCatId;
    }

    int id;
    int participantId;
    Date data;
    int dorsal;
    boolean retirat;
    int bea_id;

    public int getBea_id() {
        return bea_id;
    }

    public void setBea_id(int bea_id) {
        this.bea_id = bea_id;
    }

    int circuitsCatId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public boolean isRetirat() {
        return retirat;
    }

    public void setRetirat(boolean retirat) {
        this.retirat = retirat;
    }

    public int getCircuitsCatId() {
        return circuitsCatId;
    }

    public void setCircuitsCatId(int circuitsCatId) {
        this.circuitsCatId = circuitsCatId;
    }
}
