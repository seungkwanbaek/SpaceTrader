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

import org.w3c.dom.Entity;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {

    private List<ResourceItem> resourceList = new ArrayList<>();

    private OnResourcePriceClickListener listener;

    @NonNull
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        return new ResourceViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        ResourceItem resourceItem = resourceList.get(position);
        Log.d("APP", "Binding: " + position + " " + resourceList.get(position));
        holder.resourceName.setText(resourceItem.getResourceName());
        holder.resourcePrice.setText(Integer.toString(resourceItem.getResroucePrice()));
    }

    public int getItemCount() { return resourceList.size(); }

    public ResourceItem getItem(int position) { return resourceList.get(position); }

    public void setResourceList(HashMap<Resource, Integer> resourcePriceList) {
        for (Map.Entry<Resource, Integer> entry : resourcePriceList.entrySet()) {
            resourceList.add(new ResourceItem(entry.getKey().getName(), entry.getValue().intValue(), 0));
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
                }
            });
        }
    }

    public interface OnResourcePriceClickListener {
        void onResourceClicked(Map.Entry resourcePricePair);
    }

    public void setOnResourceClickListener(OnResourcePriceClickListener listener) {
        this.listener = listener;
    }
}
