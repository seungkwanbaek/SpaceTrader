package com.example.spacetrader.view;

import java.text.DecimalFormat;
import java.util.Locale;

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
import com.example.spacetrader.viewmodel.PlayerViewModel;

public class ShowPlayerActivity extends AppCompatActivity {
    public static final String SOLAR_SYSTEM_NAME = "SOLAR_SYSTEM_NAME";
    public static final String PLAYER_NAME = "PLAYER_NAME";
    public static final String BUY_FLAG = "1";

    private PlayerViewModel playerViewModel;
    private Player player;

    private TextView pName;
    private TextView pDifficulty;
    private TextView pPoint;
    private TextView fPoint;
    private TextView tPoint;
    private TextView ePoint;
    private TextView solarSystem;
    private TextView balance;
    private TextView shipFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_item);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        pName = findViewById(R.id.show_player_name);
        pDifficulty = findViewById(R.id.show_difficulty);
        pPoint = findViewById(R.id.text_pPoint);
        fPoint = findViewById(R.id.text_fPoint);
        tPoint = findViewById(R.id.text_tPoint);
        ePoint = findViewById(R.id.text_ePoint);
        solarSystem = findViewById(R.id.show_solar_system);
        balance = findViewById(R.id.show_balance);
        shipFuel = findViewById(R.id.show_fuel);

        if (getIntent().hasExtra(MainActivity.PLAYER_NAME)) {
            //player = (Player)getIntent().getSerializableExtra(MainActivity.PLAYER_NAME);
            player = playerViewModel.getPlayer(getIntent().getExtras().
                    getString(MainActivity.PLAYER_NAME));
            if (player == null) {
                throw new Resources.NotFoundException("[ERROR] Player username "
                        + getIntent().getExtras().getString(MainActivity.PLAYER_NAME)+" not found");
            }
            pName.setText(player.getUserName());
            pDifficulty.setText(player.getDifficulty());
            System.out.println("Pilot: " + player.getSkillPoint("Pilot"));
            pPoint.setText(String.format(Locale.US, "%d", player.getSkillPoint("Pilot")));
            fPoint.setText(String.format(Locale.US, "%d", player.getSkillPoint("Fighter")));
            tPoint.setText(String.format(Locale.US, "%d", player.getSkillPoint("Trader")));
            ePoint.setText(String.format(Locale.US, "%d", player.getSkillPoint("Engineer")));
            solarSystem.setText(player.getSolarSystem().getName());
            balance.setText(Integer.toString(player.getCurrentCredit()));
            shipFuel.setText(formatDouble(player.getShip().getFuelAmount()));
        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }

    public void onBuyPressed(View view) {
        Intent intent = new Intent(this, BuySellActivity.class);
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getText());
        intent.putExtra(PLAYER_NAME, player.getUserName());
        intent.putExtra(BUY_FLAG, "true");
        startActivity(intent);
    }

    public void onSellPressed(View view) {
        Intent intent = new Intent(this, BuySellActivity.class);
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getText());
        intent.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    public void onShipButtonPressed(View view) {
        Intent intent = new Intent( this, ShowShipActivity.class);
        intent.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(intent);
    }

    public void onTravelButtonPressed(View view) {
        Intent intent = new Intent( this, TravelActivity.class);
        intent.putExtra(PLAYER_NAME, player.getUserName());
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getText());
        startActivity(intent);
    }

    private String formatDouble(Double value) {
        final DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
}
