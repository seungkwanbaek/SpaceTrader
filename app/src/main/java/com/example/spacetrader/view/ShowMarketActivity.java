package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

public class ShowMarketActivity extends AppCompatActivity {
    private SolarSystemViewModel solarSystemViewModel;
    private PlayerViewModel playerViewModel;
    public static final String SOLAR_SYSTEM_NAME = "SOLAR_SYSTEM_NAME";
    public static final String PLAYER_NAME = "PLAYER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_main);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
    }

    public void onBuyButtonPressed(View view) {
        Intent intent = new Intent(this, BuyActivity.class);
        SolarSystem solarSystem = solarSystemViewModel.getSolarSystem(getIntent().getExtras()
                .getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME));
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getName());
        Player player = playerViewModel.getPlayer(getIntent().getExtras()
                .getString(ShowPlayerActivity.PLAYER_NAME));
        intent.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    public void onReturnPressed(View view) {
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        SolarSystem solarSystem = solarSystemViewModel.getSolarSystem(getIntent().getExtras()
                .getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME));
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getName());
        Player player = playerViewModel.getPlayer(getIntent().getExtras()
                .getString(ShowPlayerActivity.PLAYER_NAME));
        intent.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }
}
