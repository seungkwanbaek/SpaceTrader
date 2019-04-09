package com.example.spacetrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.spacetrader.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.spacetrader.entities.ResourceItem;

/**
 * The ShipAdapter class
 */
public final class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ShipViewHolder> {
    private List<ResourceItem> cargoList = new ArrayList<>();

    /**
     * onCreateViewHolder of ShipAdapter
     * @param parent the parent view group
     * @param i the position
     * @return the ship view holder
     */
    @NonNull
    @Override
    public ShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item_ship, parent, false);
        return new ShipViewHolder(itemView);
    }

    /**
     * onBindViewHolder of ShipAdapter
     * @param holder the ship view holder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(@NonNull ShipViewHolder holder, int position) {
        ResourceItem r = cargoList.get(position);
        holder.resourceName.setText(r.getResourceName());
        holder.resourceAmount.setText(Long.toString(r.getResourceAmount()));
    }

    /**
     * Get the item count
     * @return the cargo size
     */
    @Override
    public int getItemCount() { return cargoList.size(); }

    /**
     * Set up the cargo
     * @param cargo the cargo
     */
    public void setUpCargo(Map<String, Long> cargo) {
        for (String r : cargo.keySet())
            cargoList.add(new ResourceItem(r, 0, cargo.get(r)));
        notifyDataSetChanged();
    }

    final class ShipViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView resourceAmount;

        /**
         * Ship view holder
         * @param itemView the view
         */
        private ShipViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.resource_name);
            resourceAmount = itemView.findViewById(R.id.resource_amount);
        }
    }
}






