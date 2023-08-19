package com.example.tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class NewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                setResult(1, result);
                finish();
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);
                EditText day = findViewById(R.id.dayNumberInput);
                EditText month = findViewById(R.id.monthNumberInput);
                EditText year = findViewById(R.id.yearNumberInput);
                EditText hours = findViewById(R.id.hoursNumberInput);
                EditText minutes = findViewById(R.id.minutesNumberInput);
                EditText location = findViewById(R.id.location);
                EditText price = findViewById(R.id.priceNumberInput);
                EditText sellerFirstName = findViewById(R.id.firstName);
                EditText sellerLastName = findViewById(R.id.lastName);
                EditText sellerEmail = findViewById(R.id.email);
                EditText sellerPhoneNumber = findViewById(R.id.phone);


                int dayNumber = Integer.parseInt(day.getText().toString());
                int monthNumber = Integer.parseInt(month.getText().toString());
                int yearNumber = Integer.parseInt(year.getText().toString());
                int hoursNumber = Integer.parseInt(hours.getText().toString());
                int minutesNumber = Integer.parseInt(minutes.getText().toString());
                int priceNumber = Integer.parseInt(price.getText().toString());

                LocalDate ld = LocalDate.of(yearNumber, monthNumber, dayNumber);
                System.out.println(ld);
                LocalTime lt = LocalTime.of(hoursNumber, minutesNumber);
                System.out.println(lt);

                Event newEvent = new Event(name.getText().toString(),
                        ld, lt, location.getText().toString(),
                        priceNumber, sellerFirstName.getText().toString(),
                        sellerLastName.getText().toString(), sellerEmail.getText().toString(),
                        sellerPhoneNumber.getText().toString());

                Map<String, Object> newEventRecord = new HashMap<>();
                newEventRecord.put("name", newEvent.getName());
                newEventRecord.put("date", newEvent.getDate().toString());
                newEventRecord.put("time", newEvent.getTime().toString());
                newEventRecord.put("location", newEvent.getLocation());
                newEventRecord.put("price", String.valueOf(newEvent.getPrice()));
                newEventRecord.put("sellerFirstName", newEvent.getSellerFirstName());
                newEventRecord.put("sellerLastName", newEvent.getSellerLastName());
                newEventRecord.put("sellerEmail", newEvent.getSellerEmail());
                newEventRecord.put("sellerLastName", newEvent.getSellerLastName());
                newEventRecord.put("sellerPhoneNumber", newEvent.getSellerPhoneNumber());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("events").document(String.valueOf(System.currentTimeMillis()))
                        .set(newEventRecord);

                Intent result = new Intent();
                result.putExtra("Event", newEvent);
                setResult(1, result);
                finish();
            }
        });
    }
}