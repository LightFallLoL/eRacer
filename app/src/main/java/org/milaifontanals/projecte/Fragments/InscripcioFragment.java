package org.milaifontanals.projecte.Fragments;

import android.os.Bundle;
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

import org.milaifontanals.projecte.R;

import java.util.ArrayList;
import java.util.List;

public class InscripcioFragment extends Fragment {

    private FlexboxLayout flexboxLayout;
    private Spinner spnCategoria;
    private List<String> categoriesSeleccionades;

    private EditText etNumF;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSi, radioButtonNo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesSeleccionades = new ArrayList<>();
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


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapter);

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
