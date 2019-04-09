package com.example.spacetrader.view;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.example.spacetrader.R;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.entities.SolarSystemItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * SolarSystemAdapter Class
 */
public class SolarSystemAdapter extends RecyclerView.Adapter<SolarSystemAdapter.
        SolarSystemViewHolder> {
    private List<SolarSystemItem> solarSystemItemsList = new ArrayList<>();
    private double costFuel;
    private double costDistance;
    private SolarSystem solarSystem;
    private View selectedItem;

    /**
     * Get cost fuel
     * @return the costFuel
     */
    public double getCostFuel() { return costFuel; }

    /**
     * Get costDistance
     * @return the costDistance
     */
    public double getCostDistance() { return costDistance; }

    /**
     * Get solarSystem
     * @return the solarSystem
     */
    public SolarSystem getSolarSystem() { return solarSystem; }

    /**
     * onCreateViewHolder for SolarSystemViewHolder
     * @param parent the ViewGroup
     * @param i the position
     * @return the solarSystemViewHolder
     */
    @Override
    public SolarSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solar_system_item, parent, false);
        return new SolarSystemViewHolder(itemView);
    }

    /**
     * onBindViewHolder for SolarSystemViewHolder
     * @param holder the solarSystemViewHolder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(@NonNull SolarSystemViewHolder holder, final int position) {
        SolarSystemItem solarSystemItem = solarSystemItemsList.get(position);
        holder.solarSystemName.setText(solarSystemItem.getSolarSystemName());
        String showDistance = formatDouble(solarSystemItem.getSolarSystemDistance());
        String showCost = formatDouble(solarSystemItem.getCost());
        holder.solarSystemDistance.setText(showDistance);
        holder.cost.setText(showCost);
        solarSystemItemsList.set(position, solarSystemItem);
    }

    /**
     * Set up the adapter
     * @param allSolarSystems the list of all solar systems
     * @param curSolarSystem current solar systems
     * @param curSolarSystemTextView the textview for current solar system
     */
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

    /**
     * format change from double to string
     * @param value the double value
     * @return the string value
     */
    private String formatDouble(Double value) {
        final DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    /**
     * GetItemCount method
     * @return the itemCount
     */
    @Override
    public int getItemCount() { return solarSystemItemsList.size(); }

    /**
     * GetItem method
     * @param position the position
     * @return the solarSystemItem
     */
    public SolarSystemItem getItem(int position) { return solarSystemItemsList.get(position); }

    /**
     * SolarSystemViewHolder
     */
    class SolarSystemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView solarSystemName;
        private TextView solarSystemDistance;
        private TextView cost;
        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        /**
         * SolarSystemViewHolder
         * @param itemView the view
         */
        public SolarSystemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            solarSystemName = itemView.findViewById(R.id.text_solar_system_name);
            solarSystemDistance = itemView.findViewById(R.id.text_solar_system_distance);
            cost = itemView.findViewById(R.id.text_solar_system_cost);
        }

        /**
         * onClick method for view
         * @param view the view
         */
        @Override
        public void onClick(View view) {
            if (selectedItem != null) selectedItem.setSelected(false);
            selectedItem = view;
            int position = getAdapterPosition();
            selectedItems.put(getAdapterPosition(), true);
            view.setSelected(true);
            costDistance = solarSystemItemsList.get(position).getSolarSystemDistance();
            costFuel = solarSystemItemsList.get(position).getCost();
            solarSystem = solarSystemItemsList.get(position).getSolarSystem();
        }
    }
}
