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
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.TradeGood;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.entities.CargoHold;

import java.util.List;

public class ShowShipActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

    private Player player;
    private CargoHold cargoHold;

    private TextView shipType;
    private TextView pname;


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

        if (getIntent().hasExtra(ShowPlayerActivity.PLAYER_NAME)) {
            player = playerViewModel.getPlayer(getIntent().getExtras().getString(ShowPlayerActivity.PLAYER_NAME));
            Log.d("APP", "lololololol");

            player.getShipType().getCargoHold().getQuantity();

            shipType.setText(player.getShipType().getType().toString());

        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }


}
