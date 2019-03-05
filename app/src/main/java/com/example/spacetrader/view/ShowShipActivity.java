package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.ShipViewModel;

public class ShowShipActivity extends AppCompatActivity {
    private PlayerViewModel playerViewModel;
    private ShipViewModel shipViewModel;
    private ShipAdapter adapter;

    private Player player;
    private TextView shipType;
    private TextView capacity;
    private TextView remaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ship);
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        player = playerViewModel.getPlayer(getIntent().
                getExtras().getString(ShowPlayerActivity.PLAYER_NAME));

        RecyclerView recyclerView = findViewById(R.id.resource_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new ShipAdapter();
        recyclerView.setAdapter(adapter);

        shipType = findViewById(R.id.ship_type);
        capacity = findViewById(R.id.show_capacity);
        remaining = findViewById(R.id.show_used);
        shipType.setText(player.getShip().getType().toString());
        capacity.setText(String.valueOf(player.getShip().getCargoCapacity()));
        remaining.setText(String.valueOf(player.getShip().getTotalCargoAmount()));
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setUpCargo(player.getShip().getCargo());
    }

    public void onBackPressed(View view) {
        super.onBackPressed();
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        intent.putExtra(ShowPlayerActivity.PLAYER_NAME, player.getUserName());
        startActivity(intent);
        finish();
    }
}
