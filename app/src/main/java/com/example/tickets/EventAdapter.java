package com.example.tickets;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> Events;

    public EventAdapter(List<Event> events) {
        this.Events = events;
    }

    public void AddEvent(Event event) {
        Events.add(event);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Events);
    }

    public void deleteEvent(int position) {
        Events.remove(position);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Events);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = Events.get(position);
        holder.name.setText(event.getName());
        System.out.println(event.getDate());
        holder.date.setText(event.getDate().toString());
        holder.time.setText(event.getTime().toString());
        holder.location.setText(event.getLocation());
        holder.price.setText(String.valueOf(event.getPrice()) + " ILS");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SingleEventActivity.class);
                i.putExtra("Event", event);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) v.getContext(), holder.cardView, "CardViewTransition");
                v.getContext().startActivity(i, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Events.size();
    }
}
