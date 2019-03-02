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

    private PlayerViewModel playerViewModel;
    private Player player;

    private TextView pName;
    private TextView pDiffcuilty;
    private TextView pPoint;
    private TextView fPoint;
    private TextView tPoint;
    private TextView ePoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_item);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        pName = findViewById(R.id.show_player_name);
        pDiffcuilty = findViewById(R.id.show_difficulty);
        pPoint = findViewById(R.id.text_pPoint);
        fPoint = findViewById(R.id.text_fPoint);
        tPoint = findViewById(R.id.text_tPoint);
        ePoint = findViewById(R.id.text_ePoint);

        if (getIntent().hasExtra(MainActivity.PLAYER_NAME)) {
            //player = (Player)getIntent().getSerializableExtra(MainActivity.PLAYER_NAME);
            player = playerViewModel.getPlayer(getIntent().getExtras().getString(MainActivity.PLAYER_NAME));
            if (player == null) throw new Resources.NotFoundException("[ERROR] Player username not found");

            pName.setText(player.getUserName());
            pDiffcuilty.setText(player.getDifficulty());
            System.out.println("Pilot: " + player.getSkillPoint("Pilot"));
            pPoint.setText("" + player.getSkillPoint("Pilot"));
            fPoint.setText("" + player.getSkillPoint("Fighter"));
            tPoint.setText("" + player.getSkillPoint("Trader"));
            ePoint.setText("" + player.getSkillPoint("Engineer"));

            /** Set the market button */
            Button mkt = findViewById(R.id.gotoMarket);

            /** Set the ship button */
            Button ship = findViewById(R.id.gotoShip);
            ship.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ShowPlayerActivity.this, ShowShipActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }

}
