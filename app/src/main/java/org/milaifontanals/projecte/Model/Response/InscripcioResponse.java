package org.milaifontanals.projecte.Model.Response;

import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.Model.Inscripcio;

import java.util.List;

public class InscripcioResponse {
    private List<Inscripcio> inscripcions;

    public List<Inscripcio> getInscripcions() {
        return inscripcions;
    }

    public void setInscripcions(List<Inscripcio> inscripcions) {
        this.inscripcions = inscripcions;
    }
}