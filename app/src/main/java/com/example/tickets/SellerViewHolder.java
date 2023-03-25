package com.example.tickets;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SellerViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public TextView name;
    public TextView email;
    public TextView phoneNumber;

    public SellerViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        name = itemView.findViewById(R.id.nameTextView);
        email = itemView.findViewById(R.id.emailTextView);
        phoneNumber = itemView.findViewById(R.id.phoneTextView);
    }
}
