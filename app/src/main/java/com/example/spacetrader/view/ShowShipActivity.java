package com.example.spacetrader.view;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.spacetrader.R;

public class ShowShipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_ship );
    }
}
