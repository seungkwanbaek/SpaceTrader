package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.List;

public class ShowShipActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    private Player player;
    private CargoHold cargoHold;

    private TextView shipType;
    private TextView capacity;
    private TextView remaining;



    /**
     * Button handler for view back button
     *
     */
    public void onBackPressed(View view) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_ship );
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        shipType = findViewById(R.id.show_ship_type);
        capacity = findViewById(R.id.show_capacity);
        remaining = findViewById(R.id.show_remaining);

        if (getIntent().hasExtra(ShowPlayerActivity.PLAYER_NAME)) {
            player = playerViewModel.getPlayer(getIntent().getExtras().getString(ShowPlayerActivity.PLAYER_NAME));
            HashMap<CargoItem, Integer> cargo = player.getShip().getCargoHold().getCargo();


            shipType.setText(player.getShip().getType().toString());
            capacity.setText(String.valueOf(player.getShip().getCargoHold().getCapacity()));
            remaining.setText(String.valueOf(player.getShip().getCargoHold().getRemaining()));





        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }


}
