package com.example.spacetrader.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.entities.ResourceItem;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.ResourceViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuySellActivity extends AppCompatActivity {
    PlayerViewModel playerViewModel;
    SolarSystemViewModel solarSystemViewModel;
    ResourceViewModel resourceViewModel;
    private ResourceAdapter adapter;
    private SolarSystem solarSystem;
    private Player player;
    private RecyclerView resourceRecyclerView;
    private boolean buyFlag;
    private TextView balanceTextView, subTotalTextView, capacityTextView, usedCapacityTextView;
    private Button buySellButton;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_sell_page);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        resourceViewModel = ViewModelProviders.of(this).get(ResourceViewModel.class);
        solarSystem = solarSystemViewModel.getSolarSystem(getIntent().
                getExtras().getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME));
        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowPlayerActivity.PLAYER_NAME));
        buyFlag = getIntent().hasExtra(ShowPlayerActivity.BUY_FLAG);

        resourceRecyclerView = findViewById(R.id.resource_list);
        resourceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resourceRecyclerView.setHasFixedSize(true);

        buySellButton = findViewById(R.id.buy_sell_button);
        balanceTextView = findViewById(R.id.show_current_balance);
        subTotalTextView = findViewById(R.id.show_subtotal);
        capacityTextView = findViewById(R.id.show_capacity);
        usedCapacityTextView = findViewById(R.id.show_subtotal_capacity);

        balanceTextView.setText(Integer.toString(player.getCurrentCredit()));
        if (buyFlag) buySellButton.setText("BUY");
        else buySellButton.setText("SELL");

        adapter = new ResourceAdapter();
        resourceRecyclerView.setAdapter(adapter);
        Map<Integer, SolarSystem> map = new HashMap<>();
    }

    public void onSubmitPressed(View view) {
        int totalPrice = adapter.getSubTotal();
        long usedCap = adapter.getUsedCap();
        int cap = player.getShipCapacity();
        int currentBalance = player.getCurrentCredit();
        if (buyFlag) {
            if (totalPrice > currentBalance) {
                String msg = "You don't have enough balance!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                return;
            }
            if (cap <= usedCap) {
                String msg = "You don't have enough space ship capacity!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                return;
            }
            player.cost(totalPrice);
            for (int i = 0; i < adapter.getItemCount(); ++i) {
                ResourceItem r = adapter.getItem(i);
                player.loadCargo(r.getResourceName(), (int)r.getResrouceAmount());
            }
        } else {
            player.deposit(totalPrice);
            for (int i = 0; i < adapter.getItemCount(); ++i) {
                ResourceItem r = adapter.getItem(i);
                player.unloadCargo(r.getResourceName(), (int)r.getResrouceAmount());
            }
        }
        playerViewModel.setPlayer(player);
        MainActivity.myPlayerReference.setValue(player);
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (buyFlag) adapter.setUpBuyAdapter(generateResourcePriceList(),
                player.getCurrentCredit(), player.getShipCapacity(), player.getUsedCapacity(),
                balanceTextView, subTotalTextView, capacityTextView, usedCapacityTextView);
        else adapter.setUpSellAdapter(generateResourcePriceList(), player.getCargo(),
                player.getCurrentCredit(), player.getShipCapacity(), player.getUsedCapacity(),
                balanceTextView, subTotalTextView, capacityTextView, usedCapacityTextView);
    }

    public void onCancelPressed(View view) {
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
        finish();
    }

    private HashMap<String, Long> generateResourcePriceList() {
        HashMap<String, Long> priceList = new HashMap<>();
        int techLv = solarSystem.getTechLevelValue();
        List<Resource> allResources = resourceViewModel.getAllResource();
        for (Resource r : allResources)
            priceList.put(r.getName(), (long)r.getPrice(techLv));
        MainActivity.myResourceReference.setValue(priceList);
        return priceList;
    }
}
