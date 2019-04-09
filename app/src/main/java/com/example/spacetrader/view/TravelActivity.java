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

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * The travel activity
 */
public class TravelActivity extends AppCompatActivity {
    PlayerViewModel playerViewModel;
    SolarSystemViewModel solarSystemViewModel;
    private SolarSystemAdapter adapter;

    private Player player;
    private List<SolarSystem> allSolarSystems;
    private RecyclerView solarSystemRecyclerView;
    private TextView curSolarSystemView;
    private TextView curFuel;
    private HashMap<Integer, SolarSystem> solarSystemHashMap;

    /**
     * onCreate method for travel activity
     * @param savedInstanceState the bundle
     * @throws Resources.NotFoundException the notFoundException exception
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_page);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        player = playerViewModel.getPlayer(getIntent().getExtras().
                getString(ShowPlayerActivity.PLAYER_NAME));
        allSolarSystems = solarSystemViewModel.getAllSolarSystems();

        solarSystemRecyclerView = findViewById(R.id.solar_system_list);
        solarSystemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        solarSystemRecyclerView.setHasFixedSize(true);
        curSolarSystemView = findViewById(R.id.show_current_solar_system);
        curFuel = findViewById(R.id.show_current_fuel);
        adapter = new SolarSystemAdapter();
        solarSystemRecyclerView.setAdapter(adapter);
    }

    /**
     * onTravelPressed for travel button
     * @param view the view
     */
    public void onTravelPressed(View view) {
        double costFuel  = adapter.getCostFuel();
        SolarSystem destination = adapter.getSolarSystem();
        double playerFuel = player.getFuel();
        if (costFuel > playerFuel) {
            String msg = "You don't have enough fuel to travel!";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            return;
        } else {
            player.travel(destination, costFuel);
        }
        Random rand = new Random();
        int curTechLevelValue = player.getSolarSystem().getTechLevelValue();
        if (rand.nextDouble() > 0.6) {
            curTechLevelValue = curTechLevelValue > 1 ? curTechLevelValue - 2 : curTechLevelValue + 2;
        } else {
            curTechLevelValue = curTechLevelValue > 0 ? curTechLevelValue - 1 : curTechLevelValue + 1;
        }
        player.getSolarSystem().setTechLevelValue(curTechLevelValue);
        playerViewModel.setPlayer(player);
        MainActivity.myPlayerReference.setValue(player);
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, destination.getName());
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    /**
     * onCancelPressed for cancel button
     * @param view the view
     */
    public void onCancelPressed(View view) {
        String solarName = getIntent().getExtras().getString(ShowPlayerActivity.SOLAR_SYSTEM_NAME);
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.SOLAR_SYSTEM_NAME, solarName);
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
        finish();
    }

    /**
     * onResume method
     */
    @Override
    public void onResume() {
        super.onResume();
        adapter.setUpAdapter(allSolarSystems, player.getSolarSystem(), curSolarSystemView);
        String curFuelValue = formatDouble(player.getFuel());
        curFuel.setText(curFuelValue);
    }

    /**
     * change double to string
     * @param value the double value
     * @return the string value
     */
    private String formatDouble(Double value) {
        final DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

}
