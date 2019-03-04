package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.CargoItem;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.TradeGood;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.entities.CargoHold;
import com.example.spacetrader.viewmodel.ShipViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowShipActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private ShipViewModel shipViewModel;
    private ShipAdapter adapter;


    public static final String PLAYER_NAME = "PLAYER_NAME";

    private Player player;
    private CargoHold cargoHold;

    private TextView shipType;
    private TextView capacity;
    private TextView remaining;

    private TextView water;
    private TextView furs;
    private TextView food;
    private TextView ore;
    private TextView games;

    private TextView firearms;
    private TextView medicine;
    private TextView machines;
    private TextView narcotics;
    private TextView robots;


    /**
     * Button handler for view back button
     *
     */
    public void onBackPressed(View view) {
        super.onBackPressed();
        Intent i = new Intent(ShowShipActivity.this, ShowPlayerActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(i);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setResourcePriceList(player.getShip().getCargoHold().getCargoList());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_ship);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowPlayerActivity.PLAYER_NAME));

        RecyclerView recyclerView = findViewById(R.id.ship_resource_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new ShipAdapter();
        recyclerView.setAdapter(adapter);

        shipType = findViewById(R.id.show_ship_type);
        capacity = findViewById(R.id.show_capacity);
        remaining = findViewById(R.id.show_remaining);
        shipType.setText(player.getShip().getType().toString());
        capacity.setText(String.valueOf(player.getShip().getCargoHold().getCapacity()));
        remaining.setText(String.valueOf(player.getShip().getCargoHold().getRemaining()));

    }
}
