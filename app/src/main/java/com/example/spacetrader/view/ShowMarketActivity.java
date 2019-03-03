package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.PlayerViewModel;

public class ShowMarketActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate(savedInstanceState);
        setContentView();
    }

    public void onBuyPressed(View view) {

    }
}
