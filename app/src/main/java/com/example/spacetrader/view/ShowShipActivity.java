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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowShipActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;

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

        water = findViewById(R.id.show_water);
        furs = findViewById(R.id.show_furs);
        food = findViewById(R.id.show_food);
        ore = findViewById(R.id.show_ore);
        games = findViewById(R.id.show_games);
        firearms = findViewById(R.id.show_firearms);
        medicine = findViewById(R.id.show_medicine);
        machines = findViewById(R.id.show_machines);
        narcotics = findViewById(R.id.show_narcotics);
        robots = findViewById(R.id.show_robots);

        Map<String, TextView> hm = new HashMap<>();
        hm.put("water", water);
        hm.put("furs", furs);
        hm.put("food", food);
        hm.put("ore", ore);
        hm.put("games", games);
        hm.put("firearms", firearms);
        hm.put("medicine", medicine);
        hm.put("machines", machines);
        hm.put("narcotics", narcotics);
        hm.put("robots", robots);

        if (getIntent().hasExtra(ShowPlayerActivity.PLAYER_NAME)) {
            player = playerViewModel.getPlayer(getIntent().getExtras().getString(ShowPlayerActivity.PLAYER_NAME));
            HashMap<CargoItem, Integer> cargo = player.getShip().getCargoHold().getCargo();

            for (HashMap.Entry<CargoItem, Integer> entry : cargo.entrySet()) {
                TextView s =  hm.get(entry.getKey().getItemName());
                if (s == null) System.out.println("NULL TEXTVIEW");
                s.setText(String.valueOf(entry.getValue()));
            }

            shipType.setText(player.getShip().getType().toString());
            capacity.setText(String.valueOf(player.getShip().getCargoHold().getCapacity()));
            remaining.setText(String.valueOf(player.getShip().getCargoHold().getRemaining()));
        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }
}
