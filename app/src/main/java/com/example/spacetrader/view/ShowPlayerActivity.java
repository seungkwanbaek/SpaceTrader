package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.PlayerViewModel;

public class ShowPlayerActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "PLAYER_NAME";

    private PlayerViewModel playerViewModel;
    private Player player;

    private TextView pName;
    private TextView pDifficulty;
    private TextView pPoint;
    private TextView fPoint;
    private TextView tPoint;
    private TextView ePoint;
    private TextView solarSystem;

    public static final String SOLAR_SYSTEM_NAME = "SOLAR_SYSTEM_NAME";
    private static final int EDIT_REQUEST = 4;

    /**
     * Button handler for view ship button
     *
     * @param view the button
     */

    public void onShipPressed(View view) {
        Intent intent = new Intent( ShowPlayerActivity.this, ShowShipActivity.class);
        intent.putExtra(PLAYER_NAME, player.getUserName());
        startActivity(intent);
        finish();
    }


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

        if (getIntent().hasExtra(MainActivity.PLAYER_NAME)) {
            //player = (Player)getIntent().getSerializableExtra(MainActivity.PLAYER_NAME);
            player = playerViewModel.getPlayer(getIntent().getExtras().getString(MainActivity.PLAYER_NAME));
            if (player == null) throw new Resources.NotFoundException("[ERROR] Player username not found");

            pName.setText(player.getUserName());
            pDifficulty.setText(player.getDifficulty());
            System.out.println("Pilot: " + player.getSkillPoint("Pilot"));
            pPoint.setText("" + player.getSkillPoint("Pilot"));
            fPoint.setText("" + player.getSkillPoint("Fighter"));
            tPoint.setText("" + player.getSkillPoint("Trader"));
            ePoint.setText("" + player.getSkillPoint("Engineer"));
            solarSystem.setText(player.getSolarSystem().getName());

            /** Set the market button */
            Button mkt = findViewById(R.id.Market_button);

            /** Set the ship button */
            Button ship = findViewById(R.id.Ship_button);


        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }

    public void onMarketButtonPressed(View view) {
        Intent intent = new Intent(ShowPlayerActivity.this, ShowMarketActivity.class);
        intent.putExtra(SOLAR_SYSTEM_NAME, solarSystem.getText());
        startActivityForResult(intent, EDIT_REQUEST);
    }
}
