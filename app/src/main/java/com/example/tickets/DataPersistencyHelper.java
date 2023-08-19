package com.example.tickets;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataPersistencyHelper {
    public static Context context;
    private static List<Event> events = new ArrayList<Event>();

    public static void init() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("events").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    events = new ArrayList<Event>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        try {
                            Event event = new Event(
                                    document.get("name").toString(),
                                    LocalDate.parse(document.get("date").toString()),
                                    LocalTime.parse(document.get("time").toString()),
                                    document.get("location").toString(),
                                    Integer.parseInt(document.get("price").toString()),
                                    document.get("sellerFirstName").toString(),
                                    document.get("sellerLastName").toString(),
                                    document.get("sellerEmail").toString(),
                                    document.get("sellerPhoneNumber").toString()
                            );
                            events.add(event);
                        } catch (Exception e) {
                            Log.d(TAG, "onComplete: " + document);
                        }
                    }
                }
            }
        });

        db.collection("events").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                events = new ArrayList<Event>();
                for (DocumentSnapshot document : value.getDocuments()) {
                    try {
                        Event currEvent = new Event(
                                document.get("name").toString(),
                                LocalDate.parse(document.get("date").toString()),
                                LocalTime.parse(document.get("time").toString()),
                                document.get("location").toString(),
                                Integer.parseInt(document.get("price").toString()),
                                document.get("sellerFirstName").toString(),
                                document.get("sellerLastName").toString(),
                                document.get("sellerEmail").toString(),
                                document.get("sellerPhoneNumber").toString());
                        events.add(currEvent);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

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
        return events;
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
