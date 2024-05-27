package org.milaifontanals.projecte.API;

import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.Model.CursaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



    public interface ApiService {
        @GET("get_all_curses")
        Call<CursaResponse> getAllCurses();
    }
