package com.example.spacetrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.spacetrader.entities.ResourceItem;


public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ShipViewHolder> {
    private List<ResourceItem> cargoList = new ArrayList<>();

    @NonNull
    public ShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item_ship, parent, false);
        return new ShipViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ShipViewHolder holder, int position) {
        ResourceItem r = cargoList.get(position);
        holder.resourceName.setText(r.getResourceName());
        holder.resourceAmount.setText(Integer.toString(r.getResrouceAmount()));
    }

    public int getItemCount() { return cargoList.size(); }

    public void setUpCargo(HashMap<String, Integer> cargo) {
        for (String r : cargo.keySet())
            cargoList.add(new ResourceItem(r, 0, cargo.get(r)));
        notifyDataSetChanged();
    }

    class ShipViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView resourceAmount;

        private ShipViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.resource_name);
            resourceAmount = itemView.findViewById(R.id.resource_amount);
        }
    }
}






