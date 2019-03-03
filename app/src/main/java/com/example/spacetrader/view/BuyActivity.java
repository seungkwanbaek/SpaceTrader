package com.example.spacetrader.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

public class BuyActivity extends AppCompatActivity {
    PlayerViewModel playerViewModel;
    SolarSystemViewModel solarSystemViewModel;

    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView();

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);


    }
}
