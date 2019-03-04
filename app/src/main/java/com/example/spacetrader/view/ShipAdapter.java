package com.example.spacetrader.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.widget.Toast;
import com.example.spacetrader.R;
import com.example.spacetrader.entities.TradeGood;


public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.ShipViewHolder> {
    private List<Map.Entry> resourcePriceList = new ArrayList<>();
    private OnResourcePriceClickListener listener;

    @NonNull
    public ShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item_ship, parent, false);
        return new ShipViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ShipViewHolder holder, int position) {
        Map.Entry resourcePricePair = resourcePriceList.get(position);
        Log.d("APP", "Binding: " + position + " " + resourcePriceList.get(position));

        TradeGood curResource = ((TradeGood) resourcePricePair.getKey());
        Integer curAmount = (Integer)resourcePricePair.getValue();

        holder.resourceName.setText(curResource.getItemName());
        holder.amount.setText("" + curAmount);
    }

    public int getItemCount() { return resourcePriceList.size(); }

    public void setResourcePriceList(List<Map.Entry> resourcePriceList) {
        this.resourcePriceList = resourcePriceList;
        notifyDataSetChanged();
    }

    class ShipViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView amount;

        private ShipViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.text_tradegood_name);
            amount = itemView.findViewById(R.id.amount);
        }
    }

    public interface OnResourcePriceClickListener {
        void onResourceClicked(Map.Entry resourcePricePair);
    }

    public void setOnResourceClickListener(OnResourcePriceClickListener listener) {
        this.listener = listener;
    }


}






