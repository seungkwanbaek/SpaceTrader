package com.example.spacetrader.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.Resource;
import com.example.spacetrader.entities.ResourceItem;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

public class BuyActivity extends AppCompatActivity {
    PlayerViewModel playerViewModel;
    SolarSystemViewModel solarSystemViewModel;
    private ResourceAdapter adapter;
    private SolarSystem solarSystem;
    private Player player;
    private RecyclerView resourceRecyclerView;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_buy);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        solarSystem = solarSystemViewModel.getSolarSystem(getIntent().
                getExtras().getString(ShowMarketActivity.SOLAR_SYSTEM_NAME));
        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowMarketActivity.PLAYER_NAME));
        resourceRecyclerView = findViewById(R.id.resource_list);
        resourceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resourceRecyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new ResourceAdapter();
        resourceRecyclerView.setAdapter(adapter);
        //Log.d("APP", solarSystemViewModel.getAllSolarSystems().toString());
    }

    public void onBuyPressed(View view) {
        int totalPrice = 0;
        int size = ((ResourceAdapter) resourceRecyclerView.getAdapter()).getItemCount();
        for (int i = 0; i < size; i++) {
            ResourceItem r = adapter.getItem(i);
            int price = r.getResroucePrice();
            int amount = r.getResrouceAmount();
            int subtotal = price*amount;
            totalPrice += subtotal;
        }
        int currentBalance = player.getCurrentCredit();
        if (totalPrice > currentBalance) throw new Resources.NotFoundException("Balance not enough");
        player.cost(totalPrice);
        playerViewModel.setPlayer(player);
        Intent intent = new Intent(this, ShowMarketActivity.class);
        intent.putExtra(ShowMarketActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowMarketActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setResourceList(solarSystem.getPriceList());
    }
}
