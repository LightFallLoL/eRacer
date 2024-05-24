package org.milaifontanals.projecte.API;

import org.milaifontanals.projecte.Model.Cursa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

    public interface ApiService {
        @GET("get_allCurses")
        Call<List<Cursa>> getAllCurses();
    }
