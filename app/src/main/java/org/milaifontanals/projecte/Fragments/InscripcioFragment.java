package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;

import org.milaifontanals.projecte.Model.Categoria;
import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.R;

import java.util.ArrayList;
import java.util.List;

public class InscripcioFragment extends Fragment {

    private FlexboxLayout flexboxLayout;
    private Spinner spnCategoria;
    private List<Categoria> categoriesList;
    private TextView txvTitol;

    private List<String> categoriesSeleccionades;

    private EditText etNumF;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSi, radioButtonNo;
    private Cursa selectedCursa;
    private Circuit selectedCircuit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesSeleccionades = new ArrayList<>();
        if (getArguments() != null) {
            selectedCircuit = (Circuit) getArguments().getSerializable("circuit");
            selectedCursa = (Cursa) getArguments().getSerializable("cursa");
            if (selectedCircuit != null) {
                categoriesList = selectedCircuit.getCategories();
            } else {
                // Manejar el caso donde selectedCircuit es null
                Log.e("InscripcioFragment", "Circuito no recibido correctamente");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscripcio, container, false);

        spnCategoria = view.findViewById(R.id.spnCategoria);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        etNumF = view.findViewById(R.id.etNumF);
        radioGroup = view.findViewById(R.id.rgFederat);
        radioButtonSi = view.findViewById(R.id.rbSi);
        radioButtonNo = view.findViewById(R.id.rbNo);
        txvTitol = view.findViewById(R.id.tvCircuitCursa);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonSi.isChecked()) {
                    etNumF.setEnabled(true);
                } else if (radioButtonNo.isChecked()) {
                    etNumF.setEnabled(false);
                }
            }
        });
        if (categoriesList != null) {
            List<String> categoriesNames = new ArrayList<>();
            for (Categoria categoria : categoriesList) {
                categoriesNames.add(categoria.getCategoria().getCatNom());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, categoriesNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnCategoria.setAdapter(adapter);
        }
        spnCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String categoriaSeleccionada = adapterView.getItemAtPosition(i).toString();
                if (!categoriesSeleccionades.contains(categoriaSeleccionada)) {
                    categoriesSeleccionades.add(categoriaSeleccionada);
                    addCategoryChip(categoriaSeleccionada);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        txvTitol.setText(selectedCircuit.getNom() +" - "+ selectedCursa.getNom());
        return view;
    }

    private void addCategoryChip(String category) {
        final TextView chip = new TextView(getContext());
        chip.setText(category);
        chip.setPadding(8, 8, 8, 8);
        chip.setBackgroundResource(R.drawable.chip_background);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flexboxLayout.removeView(chip);
                categoriesSeleccionades.remove(category);
            }
        });
        flexboxLayout.addView(chip);
    }
}
