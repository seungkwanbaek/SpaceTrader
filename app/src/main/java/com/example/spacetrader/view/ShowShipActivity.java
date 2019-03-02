package com.example.spacetrader.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.spacetrader.R;

public class ShowShipActivity extends AppCompatActivity {

    /**
     * Button handler for view back button
     *
     * @param view the button
     */
    public void onBackPressed(View view) {
        Intent intent = new Intent( ShowShipActivity.this, ShowPlayerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_ship );





    }
}
