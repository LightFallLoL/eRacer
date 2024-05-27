package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");;

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

        if (getArguments() != null) {
            Cursa cursa = (Cursa) getArguments().getSerializable("cursa");

            if (cursa != null) {
                txvTitol.setText(cursa.getNom());
                txvDate.setText(dateFormat.format(cursa.getDataInici())); // Formatear fecha si es necesario
                txvLocation.setText(cursa.getLloc());
                txvWeb.setText(cursa.getWeb());

                // Cargar imagen usando Universal Image Loader
                ImageLoader.getInstance().displayImage(cursa.getUrlFoto(), imvCursa);

                // Configurar RecyclerView de circuits
                List<Circuit> circuits = cursa.getCircuits();
                if (circuits != null && !circuits.isEmpty()) {
                    CircuitAdapter circuitAdapter = new CircuitAdapter(circuits);
                    rcyCircuits.setLayoutManager(new LinearLayoutManager(getContext()));
                    rcyCircuits.setAdapter(circuitAdapter);
                }
            }
        }

        return view;
    }
}
