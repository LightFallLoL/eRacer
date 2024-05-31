package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.milaifontanals.projecte.API.ApiService;
import org.milaifontanals.projecte.API.RetrofitClient;
import org.milaifontanals.projecte.Adapters.LiveResultsAdapter;
import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Participant;
import org.milaifontanals.projecte.Model.Registre;
import org.milaifontanals.projecte.Model.Response.InscripcioResponse;
import org.milaifontanals.projecte.Model.Response.ParticipantResponse;
import org.milaifontanals.projecte.Model.Response.RegistreResponse;
import org.milaifontanals.projecte.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveResultFragment extends Fragment {

    private RecyclerView recyclerView;
    private LiveResultsAdapter adapter;
    private List<Registre> registreList = new ArrayList<>();
    private List<Participant> participantList = new ArrayList<>();
    private List<Inscripcio> inscripcioList = new ArrayList<>();
    private EditText searchBar;
    private Handler handler = new Handler();
    private Runnable refreshRunnable;
    private Circuit selectedCircuit;
    private Cursa selectedCursa;

    private int dataLoadCount = 0; // Counter to ensure all data is loaded

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            selectedCircuit = (Circuit) getArguments().getSerializable("circuit");
            selectedCursa = (Cursa) getArguments().getSerializable("cursa");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        searchBar = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView txvTitol = view.findViewById(R.id.tvCircuitCursa);
        txvTitol.setText(selectedCircuit.getNom() + " - " + selectedCursa.getNom());
        loadParticipants();

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterResults(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        startAutoRefresh();
        return view;
    }

    private void loadParticipants() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<ParticipantResponse> participantCall = apiService.getAllParticipants();
        participantCall.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if (response.isSuccessful()) {
                    participantList = response.body().getParticipants();
                    checkDataLoadComplete();
                    loadInscripcions();
                }
            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void loadInscripcions() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<InscripcioResponse> inscripcioCall = apiService.getAllInscripcions();
        inscripcioCall.enqueue(new Callback<InscripcioResponse>() {
            @Override
            public void onResponse(Call<InscripcioResponse> call, Response<InscripcioResponse> response) {
                if (response.isSuccessful()) {
                    inscripcioList = response.body().getInscripcions();
                    checkDataLoadComplete();
                    loadRegistres();
                }
            }

            @Override
            public void onFailure(Call<InscripcioResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void loadRegistres() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<RegistreResponse> call = apiService.getAllRegistres();
        call.enqueue(new Callback<RegistreResponse>() {
            @Override
            public void onResponse(Call<RegistreResponse> call, Response<RegistreResponse> response) {
                if (response.isSuccessful()) {
                    registreList = response.body().getRegistres();
                    checkDataLoadComplete();
                }
            }

            @Override
            public void onFailure(Call<RegistreResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void checkDataLoadComplete() {
        dataLoadCount++;
        if (dataLoadCount == 3) {
            setupAdapter();
        }
    }

    private void setupAdapter() {
        adapter = new LiveResultsAdapter(registreList, selectedCircuit, selectedCursa.getDataInici(), inscripcioList, participantList);
        recyclerView.setAdapter(adapter);
        sortAndDisplayRegistres();
    }

    private void sortAndDisplayRegistres() {
        // Sort by checkpoints completed descending and partial time ascending
        registreList.sort((r1, r2) -> {
            int checkpointComparison = Integer.compare(r2.getCheckpoint().getChkId(), r1.getCheckpoint().getChkId());
            if (checkpointComparison == 0) {
                return r1.getRegTemps().compareTo(r2.getRegTemps());
            }
            return checkpointComparison;
        });
        adapter.updateRegistres(registreList);
    }

    private void filterResults(String query) {
        List<Registre> filteredList = new ArrayList<>();
        for (Registre registre : registreList) {
            Inscripcio inscripcio = getInscripcioById(registre.getRegInsId());
            Participant participant = getParticipantById(inscripcio.getParId());
            if (participant.getNom().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(inscripcio.getDorsal()).contains(query)) {
                filteredList.add(registre);
            }
        }
        adapter.updateRegistres(filteredList);
    }

    private Inscripcio getInscripcioById(int inscripcioId) {
        for (Inscripcio inscripcio : inscripcioList) {
            if (inscripcio.getInsId() == inscripcioId) {
                return inscripcio;
            }
        }
        return null;
    }

    private Participant getParticipantById(int participantId) {
        for (Participant participant : participantList) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }

    private void startAutoRefresh() {
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                loadRegistres();
                handler.postDelayed(this, 15000); // Refresh every 15 seconds
            }
        };
        handler.post(refreshRunnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(refreshRunnable);
    }
}