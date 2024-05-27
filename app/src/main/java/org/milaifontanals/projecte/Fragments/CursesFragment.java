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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.milaifontanals.projecte.API.ApiService;
import org.milaifontanals.projecte.API.RetrofitClient;
import org.milaifontanals.projecte.Adapters.CursaAdapter;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.Model.EstatsCursa;
import org.milaifontanals.projecte.Model.Response.CursaResponse;
import org.milaifontanals.projecte.Model.Response.EstatCursaResponse;
import org.milaifontanals.projecte.R;
import org.milaifontanals.projecte.Utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CursaAdapter cursaAdapter;
    private DrawerLayout drawerLayout;
    private List<Cursa> cursaList;
    private EditText edtBuscador;
    private Spinner spinnerFilter;
    private Spinner spinnerEstat;
    private List<EstatsCursa> estatCursaList;


    private int sportTypeId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sportTypeId = getArguments().getInt("sportTypeId", -1);  // -1 es el valor por defecto para "All"
        }
        cursaList = new ArrayList<>();
        loadEstatsCursa();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curses, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setupUniversalImageLoader();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);  // Disable the title
            drawerLayout = activity.findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        ImageView imgViewIcon = view.findViewById(R.id.imvEsport);
        edtBuscador = view.findViewById(R.id.edtBuscador);
        spinnerFilter = view.findViewById(R.id.spnFiltre);
        spinnerEstat = view.findViewById(R.id.spnEstat);

            updateSportIcon(imgViewIcon, sportTypeId);


        // Configurar el Spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.date_filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(adapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.state_filters, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstat.setAdapter(stateAdapter);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.rcyCurses);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2); // Número de columnas
        recyclerView.setLayoutManager(gridLayoutManager);

        // Añadir GridItemDecoration para la separación entre items
        int space = 10; // Espacio en píxeles entre los ítems
        int color = getResources().getColor(android.R.color.darker_gray); // Color del espacio
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(getContext(), color, space);
        recyclerView.addItemDecoration(itemDecoration);

        // Inicializar el adaptador con la lista vacía
        cursaAdapter = new CursaAdapter(this, cursaList);
        recyclerView.setAdapter(cursaAdapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterCurses();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerEstat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filterCurses();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterCurses();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }


    private void loadCurses() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<CursaResponse> call = apiService.getAllCurses();
        call.enqueue(new Callback<CursaResponse>() {
            @Override
            public void onResponse(Call<CursaResponse> call, Response<CursaResponse> response) {
                if (response.isSuccessful()) {
                    List<Cursa> curses = response.body().getCurses();

                    for (Cursa cursa : curses) {
                        Log.d("CursesFragment", "Cursa: " +cursa.toString());
                    }
                    sortCursesByDateDesc(curses);
                    curses = filterCursesByState(curses);
                    cursaList = new ArrayList<>(curses);


                    if (sportTypeId != -1) {
                        cursaList = filterCursesBySportType(cursaList, sportTypeId);
                    }

                    // Actualizar el adaptador con los datos recibidos
                    cursaAdapter.setCursaList(cursaList);
                    cursaAdapter.notifyDataSetChanged();
                } else {
                    Log.e("CursesFragment", "Failed to load curses: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CursaResponse> call, Throwable t) {
                Log.e("CursesFragment", "Error loading curses", t);
            }
        });
    }


    private List<Cursa> filterCursesBySportType(List<Cursa> curses, int sportTypeId) {
        // Filtrar la lista de carreras según el ID del deporte
        return curses.stream()
                .filter(cursa -> cursa.getEsport().getId() == sportTypeId)
                .collect(Collectors.toList());
    }
    private void setupUniversalImageLoader() {
        if (getContext() == null) return;
        DisplayImageOptions dop = new DisplayImageOptions.Builder().
                showImageOnLoading(R.drawable.ic_launcher_background)
                .build();
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getContext())

                        .defaultDisplayImageOptions(dop)
                        .build();

        ImageLoader.getInstance().init(config);
    }


    private void updateSportIcon(ImageView imageView, int sportType) {

        switch (sportType) {
            case 1:
                imageView.setImageResource(R.drawable.ic_swimming);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_bicycle);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_walking);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_moto);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_running);
                break;
            case -1:
            default:
                imageView.setImageResource(R.drawable.ic_all);
                break;
        }
    }

    private void sortCursesByDateDesc(List<Cursa> curses) {
        Collections.sort(curses, new Comparator<Cursa>() {
            @Override
            public int compare(Cursa c1, Cursa c2) {
                return c2.getDataInici().compareTo(c1.getDataInici()); // Orden descendente
            }
        });
    }


    private void filterCurses() {
        String query = edtBuscador.getText().toString().toLowerCase();
        String dateFilter = spinnerFilter.getSelectedItem().toString();
        String stateFilter = spinnerEstat.getSelectedItem().toString();
        List<Cursa> filteredCurses = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Date now = new Date();

        for (Cursa cursa : cursaList) {
            boolean matchesQuery = query.isEmpty() ||
                    cursa.getNom().toLowerCase().contains(query) ||
                    cursa.getLloc().toLowerCase().contains(query);
            boolean matchesDateFilter = false;
            boolean matchesStateFilter = stateFilter.equals("Mostrar Tot") || cursa.getEstatCursa().getNom().equals(stateFilter);

            switch (dateFilter) {
                case "Mostrar tot":
                    matchesDateFilter = true;
                    break;
                case "Aquesta setmana":
                    calendar.setTime(now);
                    int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
                    calendar.setTime(cursa.getDataInici());
                    int cursaWeek = calendar.get(Calendar.WEEK_OF_YEAR);
                    matchesDateFilter = currentWeek == cursaWeek;
                    break;
                case "Aquest mes":
                    calendar.setTime(now);
                    int currentMonth = calendar.get(Calendar.MONTH);
                    calendar.setTime(cursa.getDataInici());
                    int cursaMonth = calendar.get(Calendar.MONTH);
                    matchesDateFilter = currentMonth == cursaMonth;
                    break;
                case "Aquest any":
                    calendar.setTime(now);
                    int currentYear = calendar.get(Calendar.YEAR);
                    calendar.setTime(cursa.getDataInici());
                    int cursaYear = calendar.get(Calendar.YEAR);
                    matchesDateFilter = currentYear == cursaYear;
                    break;
            }

            if (matchesQuery && matchesDateFilter && matchesStateFilter) {
                filteredCurses.add(cursa);
            }
        }

        cursaAdapter.setCursaList(filteredCurses);
        cursaAdapter.notifyDataSetChanged();
    }

    private void loadEstatsCursa() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<EstatCursaResponse> call = apiService.getAllEstatsCursa();
        call.enqueue(new Callback<EstatCursaResponse>() {
            @Override
            public void onResponse(Call<EstatCursaResponse> call, Response<EstatCursaResponse> response) {
                if (response.isSuccessful()) {
                    estatCursaList = response.body().getEstatsCursa();
                    loadCurses();
                } else {
                    Log.e("CursesFragment", "Failed to load estats_cursa: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<EstatCursaResponse> call, Throwable t) {
                Log.e("CursesFragment", "Error loading estats_cursa", t);
            }
        });
    }
    private List<Cursa> filterCursesByState(List<Cursa> curses) {
        return curses.stream()
                .filter(cursa -> {
                    String estatNom = cursa.getEstatCursa().getNom();
                    return estatNom.equals("Inscripció Oberta") || estatNom.equals("Finalitzada");
                })
                .collect(Collectors.toList());
    }
}