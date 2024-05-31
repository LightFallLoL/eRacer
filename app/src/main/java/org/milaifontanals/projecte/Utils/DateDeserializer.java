package org.milaifontanals.projecte.Utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateDeserializer per poder posarlo al RetrofitClient.
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        String dateStr = json.getAsString();
        try {
            return dateFormat2.parse(dateStr);
        } catch (ParseException e) {
            try {
                return dateFormat1.parse(dateStr);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
