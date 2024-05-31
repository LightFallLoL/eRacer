package org.milaifontanals.projecte.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.JsonObject;

import org.milaifontanals.projecte.API.ApiService;
import org.milaifontanals.projecte.API.RetrofitClient;
import org.milaifontanals.projecte.Model.Categoria;
import org.milaifontanals.projecte.Model.Circuit;
import org.milaifontanals.projecte.Model.Cursa;
import org.milaifontanals.projecte.Model.Inscripcio;
import org.milaifontanals.projecte.Model.Response.InscripcioResponse;
import org.milaifontanals.projecte.Utils.ParticipantService;
import org.milaifontanals.projecte.R;
import org.milaifontanals.projecte.Utils.InscriptionCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscripcioFragment extends Fragment {

    List<Categoria> categoriesList = new ArrayList<>();
    Categoria categoriaSeleccionadaGlobal = null;
    private FlexboxLayout flexboxLayout;
    private Spinner spnCategoria;
    private TextView txvTitol;
    private EditText etNif, etNomCognoms, etDataNaixement, etTelefon, etEmail;
    private String categoriaSeleccionada;

    private EditText etNumF;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSi, radioButtonNo;
    private Cursa selectedCursa;
    private Circuit selectedCircuit;
    private Button btnInscripcio;
    private Calendar dataActual;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCircuit = (Circuit) getArguments().getSerializable("circuit");
            selectedCursa = (Cursa) getArguments().getSerializable("cursa");
            if (selectedCircuit != null) {
                categoriesList = selectedCircuit.getCategories();
            } else {
                Log.e("InscripcioFragment", "Circuito no recibido correctamente");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscripcio, container, false);
        dataActual = Calendar.getInstance();

        etNif = view.findViewById(R.id.etNif);
        etNomCognoms = view.findViewById(R.id.etNomCognoms);
        etDataNaixement = view.findViewById(R.id.etDataNaixement);
        etTelefon = view.findViewById(R.id.etTelefon);
        etEmail = view.findViewById(R.id.etEmail);
        spnCategoria = view.findViewById(R.id.spnCategoria);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        etNumF = view.findViewById(R.id.etNumF);
        radioGroup = view.findViewById(R.id.rgFederat);
        radioButtonSi = view.findViewById(R.id.rbSi);
        radioButtonNo = view.findViewById(R.id.rbNo);
        txvTitol = view.findViewById(R.id.tvCircuitCursa);
        btnInscripcio = view.findViewById(R.id.btnInscripcio);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            etNumF.setEnabled(radioButtonSi.isChecked());
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
                String categoriaDetallSeleccionada = adapterView.getItemAtPosition(i).toString();
                categoriaSeleccionadaGlobal = obtenirCategoriaPerNom(categoriaDetallSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        view.findViewById(R.id.btnDate).setOnClickListener(v ->{
            int year = dataActual.get(Calendar.YEAR);
            int month = dataActual.get(Calendar.MONTH);
            int day = dataActual.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Dialog, (view1, year1, month1, dayOfMonth) -> {
                dataActual.set(year1, month1, dayOfMonth);
                etDataNaixement.setText(android.text.format.DateFormat.format("dd/MM/yyyy", dataActual));
            }, year, month, day);
            datePickerDialog.show();
        });
        txvTitol.setText(selectedCircuit.getNom() + " - " + selectedCursa.getNom());

        btnInscripcio.setOnClickListener(v -> {
            if (validarFormulari()) {
                registrarInscripcio();
            } else {
                Toast.makeText(getContext(), "Corregiu els errors abans de continuar", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private Categoria obtenirCategoriaPerNom(String nomCategoria) {
        for (Categoria categoria : categoriesList) {
            if (categoria.getCategoria().getCatNom().equals(nomCategoria)) {
                return categoria;
            }
        }
        return null;
    }

    private boolean validarFormulari() {
        String nif = etNif.getText().toString().trim();
        String nomCognoms = etNomCognoms.getText().toString().trim();
        String dataNaixement = etDataNaixement.getText().toString().trim();
        String telefon = etTelefon.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        boolean esFederat = radioButtonSi.isChecked();
        String numFederat = etNumF.getText().toString().trim();

        // Validació de NIF
        if (!nif.matches("^[0-9]{8}[A-Za-z]$")) {
            etNif.setError("NIF no vàlid");
            return false;
        }

        // Validació de Nom i Cognoms
        if (nomCognoms.isEmpty() || !nomCognoms.contains(" ")) {
            etNomCognoms.setError("Ha d'incloure nom i cognoms");
            return false;
        }

        // Validació de Data de Naixement
        if (!dataNaixement.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")) {
            etDataNaixement.setError("Data no vàlida");
            return false;
        }

        // Comprovar si la data de naixement és anterior a la data actual
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date dataNaix = sdf.parse(dataNaixement);
            Date avui = new Date();
            if (!dataNaix.before(avui)) {
                etDataNaixement.setError("La data de naixement ha de ser anterior a avui");
                return false;
            }
        } catch (ParseException e) {
            etDataNaixement.setError("Data no vàlida");
            return false;
        }

        // Validació de Telèfon
        if (!telefon.matches("^[0-9]{9}$")) {
            etTelefon.setError("Telèfon no vàlid");
            return false;
        }

        // Validació de Correu Electrònic
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Correu electrònic no vàlid");
            return false;
        }

        // Validació de Número de Federat (si aplica)
        if (esFederat && numFederat.isEmpty()) {
            etNumF.setError("Número de federat requerit");
            return false;
        }

        // Validació de Categoria
        if (categoriaSeleccionadaGlobal == null) {
            Toast.makeText(getContext(), "Ha de seleccionar una categoria", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registrarInscripcio() {
        String nif = etNif.getText().toString().trim();
        String nomCognoms = etNomCognoms.getText().toString().trim();
        String dataNaixement = etDataNaixement.getText().toString().trim();
        String telefon = etTelefon.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        boolean esFederat = radioButtonSi.isChecked();
        String numFederat = etNumF.getText().toString().trim();
        String nom = nomCognoms.split(" ")[0];
        String cognoms = nomCognoms.substring(nom.length()).trim();
        int num = esFederat ? 1 : 0;

        if (categoriaSeleccionadaGlobal != null && selectedCircuit != null) {
            int insCccId = obtenirInsCccId(selectedCircuit.getId(), categoriaSeleccionadaGlobal.getCatId());

            if (insCccId != -1) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                try {
                    Date dataNaix = sdf.parse(dataNaixement);
                    JsonObject requestJson = ParticipantService.createRequestJson(nif, nom, cognoms, dataNaix, telefon, email, num, insCccId, (long) selectedCircuit.getId(), (long) categoriaSeleccionadaGlobal.getCatId());
                    mostrarDialog(requestJson, email);
                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_inscripcioFragment_to_cursaFragment);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "No s'ha trobat la combinació de categoria i circuit", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Si us plau, selecciona una categoria i un circuit", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDialog(JsonObject requestJson, String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(true);
        builder.setTitle("CONFIRMAR PAGAMENT");
        builder.setMessage("Estas segur de pagar "+ selectedCircuit.getPreu() +"€?");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ParticipantService.sendInscriptionRequest(requestJson);

                getInscrits(new InscriptionCallback() {
                    @Override
                    public void onInscriptionSuccess(int inscriptionId) {
                        // Procedemos a enviar el email
                        //EmailUtil.enviarEmail(email,selectedCursa.getNom(),selectedCircuit.getNom(),inscriptionId);


                    }

                    @Override
                    public void onEmailSentSuccess() {
                        Toast.makeText(getContext(), "Email enviat amb exit!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }


    private void getInscrits(InscriptionCallback callback) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<InscripcioResponse> call = apiService.getAllInscripcions();
        call.enqueue(new Callback<InscripcioResponse>() {
            @Override
            public void onResponse(Call<InscripcioResponse> call, Response<InscripcioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Inscripcio> inscripciones = response.body().getInscripcions();
                    if (!inscripciones.isEmpty()) {
                        Inscripcio ultimaInscripcion = Collections.max(inscripciones, Comparator.comparingInt(Inscripcio::getInsId));
                        callback.onInscriptionSuccess(ultimaInscripcion.getInsId());
                    } else {
                        callback.onFailure("No inscriptions available");
                    }
                } else {
                    callback.onFailure("Failed to load inscriptions: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<InscripcioResponse> call, Throwable t) {
                callback.onFailure("Error fetching inscriptions: " + t.getMessage());
            }
        });
    }


    private int obtenirInsCccId(int cirId, int catId) {
        for (Categoria categoria : categoriesList) {
            if (categoria.getCirId() == cirId && categoria.getCatId() == catId) {
                return categoria.getCccId();
            }
        }
        return -1;
    }
}


