package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.PlayerViewModel;

public class ShowMarketActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "PLAYER_NAME";
    public static final String SOLAR_SYSTEM_NAME = "SOLAR_SYSTEM_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_main);
    }

    public void onBuyPressed(View view) {
        Intent init_intent = getIntent();
        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra(PLAYER_NAME, init_intent.getExtras().getString(PLAYER_NAME));
        intent.putExtra(SOLAR_SYSTEM_NAME, init_intent.getExtras().getString(SOLAR_SYSTEM_NAME));
        startActivity(intent);
    }
}
