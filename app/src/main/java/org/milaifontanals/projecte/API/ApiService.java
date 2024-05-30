package org.milaifontanals.projecte.API;

import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Response.CursaResponse;
import org.milaifontanals.projecte.Model.Response.EstatCursaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
        @GET("get_all_curses")
        Call<CursaResponse> getAllCurses();

        @GET("get_all_estats_cursa")
        Call<EstatCursaResponse> getAllEstatsCursa();

        @POST("store_inscripcio")
        Call<Void> storeInscripcio(@Body Inscripcio inscripcio);
    }
