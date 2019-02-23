package com.example.spacetrader.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.AddPlayerViewModel;

import org.w3c.dom.Text;

public class ShowPlayerActivity extends AppCompatActivity {

    public static final String STUDENT_DATA = "PLAYER_DATA";
    private static final int EDIT_REQUEST = 5;
    private Player player;

    private Integer playerPosition;
    private TextView pName;
    private TextView pDiffcuilty;
    private TextView pPoint;
    private TextView fPoint;
    private TextView tPoint;
    private TextView ePoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_item);

        pName = findViewById(R.id.show_player_name);
        pDiffcuilty = findViewById(R.id.show_difficulty);
        pPoint = findViewById(R.id.text_pPoint);
        fPoint = findViewById(R.id.text_fPoint);
        tPoint = findViewById(R.id.text_tPoint);
        ePoint = findViewById(R.id.text_ePoint);

        if (getIntent().hasExtra(MainActivity.PLAYER_DATA)) {
            playerPosition = (Integer)getIntent().getSerializableExtra(MainActivity.PLAYER_DATA);

            pName.setText(player.getUserName());
            pDiffcuilty.setText(player.getDifficulty());
            System.out.println("Pilot: " + player.getSkillPoint("Pilot"));
            pPoint.setText("" + player.getSkillPoint("Pilot"));
            fPoint.setText("" + player.getSkillPoint("Fighter"));
            tPoint.setText("" + player.getSkillPoint("Trader"));
            ePoint.setText("" + player.getSkillPoint("Engineer"));
        } else {
            //no course is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR < NO PLAYER PASSED");
        }
    }

}
