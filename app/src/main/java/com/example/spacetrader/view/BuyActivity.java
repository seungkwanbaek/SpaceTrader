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
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

public class BuyActivity extends AppCompatActivity {
    PlayerViewModel playerViewModel;
    SolarSystemViewModel solarSystemViewModel;
    private ResourceAdapter adapter;
    private SolarSystem solarSystem;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_buy);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        solarSystem = solarSystemViewModel.getSolarSystem(getIntent().
                getExtras().getString(ShowMarketActivity.SOLAR_SYSTEM_NAME));
        RecyclerView recyclerView = findViewById(R.id.resource_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new ResourceAdapter();
        recyclerView.setAdapter(adapter);
        Log.d("APP", solarSystemViewModel.getAllSolarSystems().toString());
    }

    public void onBuyPressed(View view) {
        Integer totalPrice = 0;

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setResourcePriceList(solarSystem.getResourcePrice());
    }
}
