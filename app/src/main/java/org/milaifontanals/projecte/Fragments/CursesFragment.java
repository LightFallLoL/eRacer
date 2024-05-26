package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.projecte.API.ApiService;
import org.milaifontanals.projecte.API.RetrofitClient;
import org.milaifontanals.projecte.Adapters.CursaAdapter;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.R;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CursaAdapter cursaAdapter;
    private String sportType;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sportType = getArguments().getString("sportType");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curses, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            DrawerLayout drawerLayout = activity.findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            loadCurses();

        }

        recyclerView = view.findViewById(R.id.rcyCurses);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;
    }

    private void loadCurses() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<Cursa>> call = apiService.getAllCurses();
        call.enqueue(new Callback<List<Cursa>>() {
            @Override
            public void onResponse(Call<List<Cursa>> call, Response<List<Cursa>> response) {
                if (response.isSuccessful()) {
                    List<Cursa> curses = response.body();
                    // Filtrar las carreras por tipo de deporte si es necesario
                    if (sportType != null && !sportType.equals("All")) {
                        curses = filterCursesBySportType(curses, sportType);
                    }
                    cursaAdapter = new CursaAdapter(getActivity(), curses);
                    recyclerView.setAdapter(cursaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cursa>> call, Throwable t) {
                // Manejar errores
            }
        });
    }

    private List<Cursa> filterCursesBySportType(List<Cursa> curses, String sportType) {
        // Filtrar la lista de carreras según el tipo de deporte
        return curses.stream()
                .filter(cursa -> cursa.getEsport().getNom().equalsIgnoreCase(sportType))
                .collect(Collectors.toList());
    }
}