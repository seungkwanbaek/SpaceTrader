package com.example.spacetrader.view;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private List<ResourceItem> resourceList = new ArrayList<>();
    private int subTotal = 0, balance = 0, cap = 0, usedCap = 0;
    private TextView balanceTextView, subTotalTextView, capacityTextView, usedCapacityTextView;

    @NonNull
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        return new ResourceViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        ResourceItem resourceItem = resourceList.get(position);
        holder.resourceName.setText(resourceItem.getResourceName());
        holder.resourcePrice.setText(Integer.toString(resourceItem.getResroucePrice()));
    }

    public int getItemCount() { return resourceList.size(); }

    public ResourceItem getItem(int position) { return resourceList.get(position); }

    public int getSubTotal() { return subTotal; }

    public int getUsedCap() { return usedCap; }

    public void setUpAdapter(HashMap<Resource, Integer> resourcePriceList, int balance_, int cap_, int usedCap_,
                             TextView balanceTextView_, TextView subTotalTextView_, TextView capacityTextView_, TextView usedCapacityTextView_) {
        this.balance = balance_;
        this.usedCap = usedCap_;
        this.cap = cap_;
        this.balanceTextView = balanceTextView_;
        this.subTotalTextView = subTotalTextView_;
        this.capacityTextView = capacityTextView_;
        this.usedCapacityTextView = usedCapacityTextView_;
        balanceTextView.setText(Integer.toString(balance));
        capacityTextView.setText(Integer.toString(cap));
        usedCapacityTextView.setText(Integer.toString(usedCap));
        for (Map.Entry<Resource, Integer> entry : resourcePriceList.entrySet()) {
            resourceList.add(new ResourceItem(entry.getKey().getName(), entry.getValue(), 0));
        }
        notifyDataSetChanged();
    }

    class ResourceViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView resourcePrice;
        private NumberPicker buyAmount;

        private ResourceViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.text_resource_name);
            resourcePrice = itemView.findViewById(R.id.text_resource_price);
            buyAmount = itemView.findViewById(R.id.np_buy_amount);
            buyAmount.setMaxValue(100);
            buyAmount.setMinValue(0);
            buyAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    int position = getAdapterPosition();
                    resourceList.get(position).setResourceAmount(newVal);
                    subTotal += (newVal-oldVal)*resourceList.get(position).getResroucePrice();
                    subTotalTextView.setText(Integer.toString(subTotal));
                    usedCap += newVal-oldVal;
                    usedCapacityTextView.setText(Integer.toString(usedCap));
                }
            });
        }
    }
}
