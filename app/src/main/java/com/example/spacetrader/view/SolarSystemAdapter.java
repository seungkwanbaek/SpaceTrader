package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.entities.ResourceItem;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.entities.SolarSystemItem;
import com.example.spacetrader.viewmodel.PlayerViewModel;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;


public class SolarSystemAdapter extends RecyclerView.Adapter<SolarSystemAdapter.
        SolarSystemViewHolder> {
    private List<SolarSystemItem> solarSystemItemsList = new ArrayList<>();
    private double costFuel;
    private double costDistance;
    private SolarSystem solarSystem;

    public double getCostFuel() { return costFuel; }
    public double getCostDistance() { return costDistance; }
    public SolarSystem getSolarSystem() { return solarSystem; }

    public SolarSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solar_system_item, parent, false);
        return new SolarSystemViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull SolarSystemViewHolder holder, final int position) {
        SolarSystemItem solarSystemItem = solarSystemItemsList.get(position);
        holder.solarSystemName.setText(solarSystemItem.getSolarSystemName());
        String showDistance = formatDouble(solarSystemItem.getSolarSystemDistance());
        String showCost = formatDouble(solarSystemItem.getCost());
        holder.solarSystemDistance.setText(showDistance);
        holder.cost.setText(showCost);
        solarSystemItemsList.set(position, solarSystemItem);
    }

    public void setUpAdapter(List<SolarSystem> allSolarSystems, SolarSystem curSolarSystem,
                             TextView curSolarSystemTextView) {
        int cur_x = curSolarSystem.getX();
        int cur_y = curSolarSystem.getY();
        String curSolarSystemName = curSolarSystem.getName();
        for (SolarSystem solarSystem : allSolarSystems) {
            int x = solarSystem.getX();
            int y = solarSystem.getY();
            if (solarSystem.equals(curSolarSystem)) {
                continue;
            }
            solarSystemItemsList.add(new SolarSystemItem(solarSystem, x, y, cur_x, cur_y));
        }
        curSolarSystemTextView.setText(curSolarSystemName);
        notifyDataSetChanged();
    }

    private String formatDouble(Double value) {
        final DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    public int getItemCount() { return solarSystemItemsList.size(); }

    public SolarSystemItem getItem(int position) { return solarSystemItemsList.get(position); }

    class SolarSystemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView solarSystemName;
        private TextView solarSystemDistance;
        private TextView cost;
        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        public SolarSystemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            solarSystemName = itemView.findViewById(R.id.text_solar_system_name);
            solarSystemDistance = itemView.findViewById(R.id.text_solar_system_distance);
            cost = itemView.findViewById(R.id.text_solar_system_cost);
        }

        public void onClick(View view) {
            int position = getAdapterPosition();
            System.out.println("position" + position + " Click works");
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                view.setSelected(false);
            }
            else {
                int i = getAdapterPosition();
                selectedItems.put(getAdapterPosition(), true);
                view.setSelected(true);
                costDistance = solarSystemItemsList.get(i).getSolarSystemDistance();
                costFuel = solarSystemItemsList.get(i).getCost();
                solarSystem = solarSystemItemsList.get(i).getSolarSystem();
            }
        }
    }
}
