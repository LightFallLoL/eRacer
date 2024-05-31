package org.milaifontanals.projecte.Utils;

public interface InscriptionCallback {
    void onInscriptionSuccess(int inscriptionId);
    void onEmailSentSuccess();
    void onFailure(String error);
}
