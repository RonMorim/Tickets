package com.example.tickets;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView name;
    public TextView date;
    public TextView time;
    public TextView location;
    public TextView price;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        name = itemView.findViewById(R.id.nameTextView);
        date = itemView.findViewById(R.id.dateTextView);
        time = itemView.findViewById(R.id.timeTextView);
        location = itemView.findViewById(R.id.locationTextView);
        price = itemView.findViewById(R.id.priceTextView);
    }
}
