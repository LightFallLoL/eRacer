package org.milaifontanals.projecte.Utils;

//Inscripcio interface per a poder controlar si succeix correctament
public interface InscriptionCallback {
    void onInscriptionSuccess(int inscriptionId);
    void onEmailSentSuccess();
    void onFailure(String error);
}
