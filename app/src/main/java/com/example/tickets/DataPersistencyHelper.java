package com.example.tickets;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {
    public static Context context;

    public static void StoreData(List<Event> events) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String json = gson.toJson(events);

        editor.putString("events", json);
        editor.commit();
    }

    public static List<Event> LoadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString("events", null);

        if(json != null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (dataAsJson, type, jsonDeserializationContext) ->
                            LocalTime.parse(dataAsJson.getAsJsonPrimitive().getAsString()))
                    .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (dataAsJson, type, jsonDeserializationContext) ->
                            LocalDate.parse(dataAsJson.getAsJsonPrimitive().getAsString()))
                    .create();


            Type type = new TypeToken<List<Event>>() {}.getType();
            List<Event> events = gson.fromJson(json, type);

            return events;
        } else {
            List<Event> events = new ArrayList<>();
            events.add(new Event("Event 1", LocalDate.of(2023, 4, 25), LocalTime.of(23, 00), "Tel Aviv", 250, "Ron", "Morim", "ron.morim@gmail.com", "0541112223"));
            events.add(new Event("Event 2", LocalDate.of(2023, 3, 28), LocalTime.of(22, 00), "Herzelia", 200, "Ron", "Morim", "ron.morim@gmail.com", "0541112223"));
            events.add(new Event("Event 3", LocalDate.of(2023, 10, 3), LocalTime.of(12, 00), "Eilat", 100,"Ron", "Morim", "ron.morim@gmail.com", "0541112223"));
            events.add(new Event("Event 4", LocalDate.of(2023, 7, 1), LocalTime.of(20, 30), "New York", 500, "Ron", "Morim", "ron.morim@gmail.com", "0541112223"));
            return events;
        }
    }

    private static class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);
        }

        @Override
        public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME));
        }
    }

    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
        }

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}
