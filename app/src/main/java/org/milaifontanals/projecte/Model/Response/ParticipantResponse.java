package org.milaifontanals.projecte.Model.Response;

import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Participant;

import java.util.List;

public class ParticipantResponse {
    private List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}

