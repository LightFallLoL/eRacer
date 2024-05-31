package org.milaifontanals.projecte.API;

import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Response.CursaResponse;
import org.milaifontanals.projecte.Model.Response.EstatCursaResponse;
import org.milaifontanals.projecte.Model.Response.InscripcioResponse;
import org.milaifontanals.projecte.Model.Response.ParticipantResponse;
import org.milaifontanals.projecte.Model.Response.RegistreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Crides al webService.
 */

public interface ApiService {
        @GET("get_all_curses")
        Call<CursaResponse> getAllCurses();

        @GET("get_all_estats_cursa")
        Call<EstatCursaResponse> getAllEstatsCursa();

        @GET("get_all_registres")
        Call<RegistreResponse> getAllRegistres();

        @GET("get_all_inscripcions")
        Call<InscripcioResponse> getAllInscripcions();

        @GET("get_all_participants")
        Call<ParticipantResponse> getAllParticipants();
        @POST("store_inscripcio")
        Call<Void> storeInscripcio(@Body Inscripcio inscripcio);


}
