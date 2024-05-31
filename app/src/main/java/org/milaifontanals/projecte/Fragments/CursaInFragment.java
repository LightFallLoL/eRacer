package org.milaifontanals.projecte.Fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
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
    private TextView txvLocation, txvEstat;
    private TextView txvWeb;
    private ImageView imvCursa, circleView;
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
        circleView = view.findViewById(R.id.circleView);
        txvEstat = view.findViewById(R.id.txvEstat);
        txvTitol.setText(cursa.getNom());
        txvDate.setText(dateFormat.format(cursa.getDataInici())); // Formatear fecha si es necesario
        txvLocation.setText(cursa.getLloc());
        txvWeb.setText(cursa.getWeb());
        txvWeb.setMovementMethod(LinkMovementMethod.getInstance());
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
            updateCircleColor(3);
            txvEstat.setText("Finalitzada");
            Button btnInscripcio = view.findViewById(R.id.btnInscripcio);
            btnInscripcio.setOnClickListener(v -> {
                Circuit selectedCircuit = circuitAdapter.getSelectedCircuit();
                if (selectedCircuit != null) {
                    // Pasar los datos del circuito seleccionado a la siguiente pantalla o fragmento
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cursa", cursa);
                    bundle.putSerializable("circuit", selectedCircuit);

                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_cursaDetailFragment_to_liveResults, bundle);
                } else {
                    Toast.makeText(getContext(), "No has seleccionat ningun circuit!", Toast.LENGTH_SHORT).show();
                }
            });
        }else if ("Inscripció Oberta".equals(cursa.getEstatCursa().getNom())) {
            Button btnInscripcio = view.findViewById(R.id.btnInscripcio);
            updateCircleColor(1);
            txvEstat.setText("Inscripció Oberta");
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
                    Toast.makeText(getContext(), "No has seleccionat ningun circuit!", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            btnInscripcio.setText("Mostrar Live Resultats");
            Button btnInscripcio = view.findViewById(R.id.btnInscripcio);
            txvEstat.setText("En Curs");
            updateCircleColor(2);
            btnInscripcio.setOnClickListener(v -> {
                Circuit selectedCircuit = circuitAdapter.getSelectedCircuit();
                if (selectedCircuit != null) {
                    // Pasar los datos del circuito seleccionado a la siguiente pantalla o fragmento
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cursa", cursa);
                    bundle.putSerializable("circuit", selectedCircuit);

                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_cursaDetailFragment_to_liveResults, bundle);
                } else {
                    Toast.makeText(getContext(), "No has seleccionat ningun circuit!", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }

    private void updateCircleColor(int status) {
        int color;
        switch (status) {
            case 1:
                color = getResources().getColor(R.color.iob);
                break;
            case 2:
                color = getResources().getColor(R.color.enCurs);
                break;
            case 3:
                color = getResources().getColor(R.color.vermell);
                break;
            default:
                color = getResources().getColor(R.color.black);
                break;
        }

        GradientDrawable drawable = (GradientDrawable) circleView.getBackground();
        drawable.setColor(color);
    }

}
