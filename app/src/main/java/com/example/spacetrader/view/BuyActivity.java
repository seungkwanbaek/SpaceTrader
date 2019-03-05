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
    private TextView balanceTextView, subTotalTextView;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_buy);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        solarSystem = solarSystemViewModel.getSolarSystem(getIntent().
                getExtras().getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME));
        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowPlayerActivity.PLAYER_NAME));
        resourceRecyclerView = findViewById(R.id.resource_list);
        resourceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resourceRecyclerView.setHasFixedSize(true);

        balanceTextView = findViewById(R.id.show_current_balance);
        subTotalTextView = findViewById(R.id.show_subtotal);

        // Setup the adapter for this recycler view
        adapter = new ResourceAdapter();
        resourceRecyclerView.setAdapter(adapter);
        //Log.d("APP", solarSystemViewModel.getAllSolarSystems().toString());
    }

    public void onBuyPressed(View view) {
        int totalPrice = adapter.getSubTotal();
        int currentBalance = player.getCurrentCredit();
        if (totalPrice > currentBalance) throw new Resources.NotFoundException("Balance not enough");
        player.cost(totalPrice);
        playerViewModel.setPlayer(player);
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setUpAdapter(solarSystem.getPriceList(), player.getCurrentCredit(), balanceTextView, subTotalTextView);
    }

    public void onCancelPressed() {
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }
}
