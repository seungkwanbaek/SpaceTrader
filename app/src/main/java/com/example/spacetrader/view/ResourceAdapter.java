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

public final class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private List<ResourceItem> resourceList = new ArrayList<>();
    private boolean buyFlag = true;
    private int subTotal = 0, balance = 0;
    private long cap = 0, usedCap = 0;
    private TextView balanceTextView, subTotalTextView, capacityTextView, usedCapacityTextView;

    /**
     * Create the resource list in buy/sell page
     * @param parent
     * @param i
     * @return
     */
    @NonNull
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        return new ResourceViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        ResourceItem resourceItem = resourceList.get(position);
        holder.resourceName.setText(resourceItem.getResourceName());
        holder.resourcePrice.setText(Long.toString(resourceItem.getResroucePrice()));
        if (resourceItem.getResrouceAmount() == 0) holder.buyAmount.setMaxValue(100);
        else holder.buyAmount.setMaxValue((int)resourceItem.getResrouceAmount());
        holder.buyAmount.setMinValue(0);
        holder.buyAmount.setValue(0);
        resourceItem.setResourceAmount(0);
        resourceList.set(position, resourceItem);
    }

    public int getItemCount() { return resourceList.size(); }

    public ResourceItem getItem(int position) { return resourceList.get(position); }

    public int getSubTotal() { return subTotal; }

    public long getUsedCap() { return usedCap; }

    private void setUpAdapter(int balance_, long cap_, long usedCap_,
                              TextView balanceTextView_, TextView subTotalTextView_, TextView capacityTextView_, TextView usedCapacityTextView_) {
        this.balance = balance_;
        this.usedCap = usedCap_;
        this.cap = cap_;
        this.balanceTextView = balanceTextView_;
        this.subTotalTextView = subTotalTextView_;
        this.capacityTextView = capacityTextView_;
        this.usedCapacityTextView = usedCapacityTextView_;
        balanceTextView.setText(Integer.toString(balance));
        capacityTextView.setText(Long.toString(cap));
        usedCapacityTextView.setText(Long.toString(usedCap));
    }

    public void setUpBuyAdapter(Map<String, Long> resourcePriceList, int balance_, long cap_, long usedCap_,
                             TextView balanceTextView_, TextView subTotalTextView_, TextView capacityTextView_, TextView usedCapacityTextView_) {
        setUpAdapter(balance_, cap_, usedCap_, balanceTextView_, subTotalTextView_, capacityTextView_, usedCapacityTextView_);

        for (Map.Entry<String, Long> entry : resourcePriceList.entrySet()) {
            resourceList.add(new ResourceItem(entry.getKey(), entry.getValue(), 0));
        }
        notifyDataSetChanged();
    }

    public void setUpSellAdapter(Map<String, Long> resourcePriceList, HashMap<String, Long> cargo, int balance_, long cap_, long usedCap_,
                                TextView balanceTextView_, TextView subTotalTextView_, TextView capacityTextView_, TextView usedCapacityTextView_) {
        setUpAdapter(balance_, cap_, usedCap_, balanceTextView_, subTotalTextView_, capacityTextView_, usedCapacityTextView_);
        this.buyFlag = false;
        for (Map.Entry<String, Long> entry : resourcePriceList.entrySet()) {
            String rName = entry.getKey();
            if (cargo.containsKey(rName))
                resourceList.add(new ResourceItem(entry.getKey(), entry.getValue(), cargo.get(rName)));
        }
        notifyDataSetChanged();
    }

    final class ResourceViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView resourcePrice;
        private NumberPicker buyAmount;

        private ResourceViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.text_resource_name);
            resourcePrice = itemView.findViewById(R.id.text_resource_price);
            buyAmount = itemView.findViewById(R.id.np_buy_amount);
            buyAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    int position = getAdapterPosition();
                    resourceList.get(position).setResourceAmount(newVal);
                    subTotal += (newVal-oldVal)*resourceList.get(position).getResroucePrice();
                    subTotalTextView.setText(Integer.toString(subTotal));
                    if (buyFlag) usedCap += newVal-oldVal;
                    else usedCap -= newVal-oldVal;
                    usedCapacityTextView.setText(Long.toString(usedCap));
                }
            });
        }
    }
}
