package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.projecte.Adapters.CircuitAdapter;
import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class CursaInFragment extends Fragment {
    private TextView txvTitol;
    private TextView txvDate;
    private TextView txvLocation;
    private TextView txvWeb;
    private ImageView imvCursa;
    private RecyclerView rcyCircuits;
    private Button btnInscripcio;
    private CircuitAdapter circuitAdapter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");;

    private Circuit selectedCircuit;
    private Cursa cursa;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cursa = (Cursa) getArguments().getSerializable("cursa");
            selectedCircuit = (Circuit) getArguments().getSerializable("selectedCircuit");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_cursa, container, false);

        txvTitol = view.findViewById(R.id.txvTitol);
        txvDate = view.findViewById(R.id.txvDate);
        txvLocation = view.findViewById(R.id.txvLocation);
        txvWeb = view.findViewById(R.id.txvWeb);
        imvCursa = view.findViewById(R.id.imvCursa);
        rcyCircuits = view.findViewById(R.id.rcyCircuits);
        btnInscripcio = view.findViewById(R.id.btnInscripcio);

        txvTitol.setText(cursa.getNom());
        txvDate.setText(dateFormat.format(cursa.getDataInici())); // Formatear fecha si es necesario
        txvLocation.setText(cursa.getLloc());
        txvWeb.setText(cursa.getWeb());
        ImageLoader.getInstance().displayImage(cursa.getUrlFoto(), imvCursa);
        // Configurar RecyclerView de circuits
        List<Circuit> circuits = cursa.getCircuits();
        if (circuits != null && !circuits.isEmpty()) {
            circuitAdapter = new CircuitAdapter(circuits);
            rcyCircuits.setLayoutManager(new LinearLayoutManager(getContext()));
            rcyCircuits.setAdapter(circuitAdapter);
        }

        if ("Finalitzada".equals(cursa.getEstatCursa().getNom())) {
            btnInscripcio.setText("Mostrar Resultats");
            btnInscripcio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the results fragment (assuming you will create this in the future)
                    NavController navController = Navigation.findNavController(view);
                    //navController.navigate(R.id.action_cursaDetailFragment_to_resultsFragment);
                }
            });
        }else {
            Button btnInscripcio = view.findViewById(R.id.btnInscripcio);
            btnInscripcio.setOnClickListener(v -> {
                Circuit selectedCircuit = circuitAdapter.getSelectedCircuit();
                if (selectedCircuit != null) {
                    // Pasar los datos del circuito seleccionado a la siguiente pantalla o fragmento
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cursa", cursa);
                    bundle.putSerializable("circuit", selectedCircuit);

                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_cursaDetailFragment_to_inscripcioFragment, bundle);
                } else {
                    Toast.makeText(getContext(), "No hay ningún circuito seleccionado", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }
}
