package com.example.spacetrader.view;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Resource;

import org.w3c.dom.Entity;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {
    private List<Map.Entry> resourcePriceList = new ArrayList<>();

    private OnResourcePriceClickListener listener;

    public ResourceViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_item, parent, false);
        return new ResourceViewHolder(itemView);
    }

    public void onBindViewHolder(ResourceViewHolder holder, int position) {
        Map.Entry resourcePricePair = resourcePriceList.get(position);
        Log.d("APP", "Binding: " + position + " " + resourcePriceList.get(position));
        Resource curResource = ((Resource)resourcePricePair.getKey());
        Integer curPrice = (Integer)resourcePricePair.getValue();
        holder.resourceName.setText(curResource.getName());
        holder.resourcePrice.setText("" + curPrice);
    }

    public int getItemCount() { return resourcePriceList.size(); }

    public void setResourcePriceList(List<Map.Entry> resourcePriceList) {
        this.resourcePriceList = resourcePriceList;
        notifyDataSetChanged();
    }

    private void setNumberPicker(NumberPicker np) {
        np.setMinValue(0);
        np.setOnValueChangedListener(onValueChangeListener);
    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                }
            };


    class ResourceViewHolder extends RecyclerView.ViewHolder {
        private TextView resourceName;
        private TextView resourcePrice;
        private NumberPicker buyAmount;

        public ResourceViewHolder(View itemView) {
            super(itemView);
            resourceName = itemView.findViewById(R.id.text_resource_name);
            resourcePrice = itemView.findViewById(R.id.text_resource_price);
            buyAmount = itemView.findViewById(R.id.np_buy_amount);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onResourceClicked(resourcePriceList.get(position));
                    }
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
