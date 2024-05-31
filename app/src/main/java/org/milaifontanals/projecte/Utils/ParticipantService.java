package org.milaifontanals.projecte.Utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ParticipantService {


    private static String urlip = "192.168.1.17";

    public static JsonObject createRequestJson(String nif, String nom, String cognoms, Date dataNaixement, String telefon, String email, int esFederat, int cursaId, Long circuitId, Long categoriaId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        JsonObject participantJson = new JsonObject();
        participantJson.addProperty("par_nif", nif);
        participantJson.addProperty("par_nom", nom);
        participantJson.addProperty("par_cognoms", cognoms);
        participantJson.addProperty("par_data_naixement", dateFormat.format(dataNaixement));
        participantJson.addProperty("par_telefon", telefon);
        participantJson.addProperty("par_email", email);
        participantJson.addProperty("par_es_federat", esFederat);

        JsonObject inscripcioJson = new JsonObject();
        inscripcioJson.addProperty("ins_ccc_id", cursaId);
        inscripcioJson.addProperty("ins_cir_id", circuitId); // Añadir circuito
        inscripcioJson.addProperty("ins_cat_id", categoriaId); // Añadir categoría

        JsonObject json = new JsonObject();
        json.add("participant", participantJson);
        json.add("inscripcio", inscripcioJson);

        return json;
    }

    public static void sendInscriptionRequest(JsonObject requestJson) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"),
                requestJson.toString()
        );

        // Imprimir la solicitud JSON para depuración
        System.out.println("Request JSON: " + requestJson.toString());

        Request request = new Request.Builder()
                .url("http://"+urlip+"/projecteApp/public/api/store_inscripcio")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(okhttp3.Call call, Response response) {
                if (!response.isSuccessful()) {
                    try {
                        throw new IOException("Unexpected code " + response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Manejar la respuesta del servidor
                try {
                    String responseBody = response.body().string();
                    System.out.println("Response: " + responseBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static boolean existParticipantByNif(String nif) {
        String jsonUrl = "http://"+urlip+"/projecteApp/public/api/get_all_participants";

        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String jsonString = convertStreamToString(inputStream);

                return checkParticipantExists(jsonString, nif);
            } else {
                throw new IOException("Error al obtener el JSON desde la URL: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        scanner.useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private static boolean checkParticipantExists(String jsonString, String nif) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("participants");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject participantJson = jsonArray.get(i).getAsJsonObject();
            String participantNif = participantJson.get("par_nif").getAsString();
            if (participantNif.equals(nif)) {
                return true;
            }
        }

        return false;
    }
}