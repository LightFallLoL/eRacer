package org.milaifontanals.projecte.API;

import org.milaifontanals.projecte.Model.Response.CursaResponse;
import org.milaifontanals.projecte.Model.Response.EstatCursaResponse;

import retrofit2.Call;
import retrofit2.http.GET;



    public interface ApiService {
        @GET("get_all_curses")
        Call<CursaResponse> getAllCurses();

        @GET("get_all_estats_cursa")
        Call<EstatCursaResponse> getAllEstatsCursa();
    }
