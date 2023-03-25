package com.example.tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);

        Bundle b = getIntent().getExtras();
        Event event = (Event) b.getSerializable("Event");

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                setResult(1, result);
                finish();
            }
        });

        TextView name = findViewById(R.id.name);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        TextView location = findViewById(R.id.location);
        TextView price = findViewById(R.id.price);
        TextView sellerName = findViewById(R.id.sellerName);
        TextView sellerEmail = findViewById(R.id.sellerEmail);
        TextView sellerPhoneNumber = findViewById(R.id.sellerPhoneNumber);

        name.setText(event.getName());
        date.setText(event.getDate().toString());
        time.setText(event.getTime().toString());
        location.setText(event.getLocation());
        price.setText(String.valueOf(event.getPrice()) + " ILS");
        sellerName.setText(event.getSellerFirstName() + " " + event.getSellerLastName());
        sellerEmail.setText(event.getSellerEmail());
        sellerPhoneNumber.setText(event.getSellerPhoneNumber());
    }
}