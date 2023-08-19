package com.example.tickets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SellerAdapter extends RecyclerView.Adapter<SellerViewHolder> {
    private List<Seller> Sellers = new ArrayList<>();

    public SellerAdapter(List<Event> events) {
        for(Event event : events) {
            if(isInList(event) == false) {
                Seller seller = new Seller(event.getSellerFirstName(), event.getSellerLastName(), event.getSellerEmail(), event.getSellerPhoneNumber());
                Sellers.add(seller);
            }
        }
    }

    private boolean isInList(Event event) {
        if(Sellers.size() == 0) {
            return false;
        }
        for (Seller seller : Sellers) {
            if(event.getSellerFirstName().equals(seller.getFirstName()) && event.getSellerLastName().equals(seller.getLastName())){
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public SellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller, parent, false);
        SellerViewHolder viewHolder = new SellerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SellerViewHolder holder, int position) {
        Seller seller = Sellers.get(position);
        holder.name.setText(seller.getFirstName() + " " + seller.getLastName());
        holder.email.setText(seller.getEmail());
        holder.phoneNumber.setText(seller.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return Sellers.size();
    }
}
