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
import android.util.Log;

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
    private TextView balanceTextView, subTotalTextView, capcaityTextView, usedCapacityTextView;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_buy);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        solarSystem = solarSystemViewModel.getSolarSystem(getIntent().
                getExtras().getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME));
        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowPlayerActivity.PLAYER_NAME));

        //if (player == null) throw new Resources.NotFoundException("[ERROR] Player username "+getIntent().getExtras().getString(MainActivity.PLAYER_NAME)+" not found");

        resourceRecyclerView = findViewById(R.id.resource_list);
        resourceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resourceRecyclerView.setHasFixedSize(true);

        balanceTextView = findViewById(R.id.show_current_balance);
        subTotalTextView = findViewById(R.id.show_subtotal);
        capcaityTextView = findViewById(R.id.show_capacity);
        usedCapacityTextView = findViewById(R.id.show_subtotal_capacity);
        balanceTextView.setText(Integer.toString(player.getCurrentCredit()));

        adapter = new ResourceAdapter();
        resourceRecyclerView.setAdapter(adapter);
    }

    public void onBuyPressed(View view) {
        int totalPrice = adapter.getSubTotal();
        int usedCap = adapter.getUsedCap();
        int cap = player.getShipCapacity();
        int currentBalance = player.getCurrentCredit();
        if (totalPrice > currentBalance) {
            String msg = "You don't have enough balance!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } else if (cap <= usedCap) {
            String msg = "You don't have enough space ship capacity!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } else {
            player.cost(totalPrice);
            for (int i = 0; i < adapter.getItemCount(); ++i) {
                ResourceItem r = adapter.getItem(i);
                player.loadCargo(r.getResourceName(), r.getResrouceAmount());
            }
            playerViewModel.setPlayer(player);
            Intent intent = new Intent(this, ShowPlayerActivity.class);
            intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
            intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setUpAdapter(solarSystem.getPriceList(),
                player.getCurrentCredit(), player.getShipCapacity(), player.getUsedCapacity(),
                balanceTextView, subTotalTextView, capcaityTextView, usedCapacityTextView);
    }

    public void onCancelPressed(View view) {
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarSystem.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }
}
