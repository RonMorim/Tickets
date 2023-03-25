package com.example.tickets;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AllEvents extends AppCompatActivity {
    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        newEventResult(data);
                    }
                }
        );

        DataPersistencyHelper.context = getApplicationContext();
        List<Event> events = DataPersistencyHelper.LoadData();

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        helper.attachToRecyclerView(recyclerView);

        FloatingActionButton btn = findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewEventActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) v.getContext(),
                        btn,
                        "ButtonTransition"
                );
                activityResultLauncher.launch(i, options);
            }
        });

        ImageView imageView = findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                setResult(1, result);
                finish();
            }
        });
    }

    protected void newEventResult(Intent data) {
        Bundle b = data.getExtras();
        Event event = (Event) b.getSerializable("Event");
        adapter.AddEvent(event);
    }
}