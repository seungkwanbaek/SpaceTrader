package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Difficulty;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.model.SolarSystemInteractor;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String PLAYER_NAME = "PLAYER_NAME";
    private static final int EDIT_REQUEST = 5;

    final Context context = this;

    private PlayerViewModel playerViewModel;
    private SolarSystemViewModel solarSystemViewModel;

    /* All the xml components */
    private EditText nameField;
    private Spinner difficultySpinner;
    private NumberPicker pPoint;
    private NumberPicker fPoint;
    private NumberPicker tPoint;
    private NumberPicker ePoint;

    private SolarSystemInteractor solarSystemInteractor = Model.getInstance().getSolarSystemInteractor();

    /** Value Change Listener for NumberPicker */
    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(MainActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

        nameField = findViewById(R.id.username_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        /** Set the numberPicker for skillPoints */
        pPoint = findViewById(R.id.pilot_point);
        fPoint = findViewById(R.id.fighter_point);
        tPoint = findViewById(R.id.trader_point);
        ePoint = findViewById(R.id.engineer_point);

        setNumberPicker(pPoint);
        setNumberPicker(fPoint);
        setNumberPicker(tPoint);
        setNumberPicker(ePoint);
    }

    /**
     * Button handler for the OK button
     *
     * @param view the button that was pressed
     */
    public void onOKPressed(View view) {
        int pValue = pPoint.getValue();
        int fValue = fPoint.getValue();
        int tValue = tPoint.getValue();
        int eValue = ePoint.getValue();

        String pName = nameField.getText().toString();
        String pDifficulty = difficultySpinner.getSelectedItem().toString();
        int skillPointsSum = pValue + fValue + tValue + eValue;

        if (pName.equals("")) {
            String res = "Please enter your userName!";
            Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
        } else if (skillPointsSum > 16) {
            String res = "Skill points cannot exceed 16!";
            Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
        } else if (skillPointsSum < 16) {
            String res = "Please allocate all skill points!";
            Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
        } else {
            initializeUniverse();
            printUniverse();
            SolarSystem selectedSolarSystem = solarSystemViewModel.getAllSolarSystems().get(0);
            Player player = new Player(pName, pDifficulty,
                    new ArrayList<>((Arrays.asList(pValue, fValue, tValue, eValue))),
                    selectedSolarSystem );
            playerViewModel.addPlayer(player);
            Intent intent = new Intent(MainActivity.this, ShowPlayerActivity.class);
            intent.putExtra(PLAYER_NAME, pName);
            startActivityForResult(intent, EDIT_REQUEST);
        }
    }

    /**
     * Button handler for the exit button
     *
     * @param view the button that was pressed
     */
    public void onExitPressed(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder
                .setMessage("Are you sure to exit?")
                .setCancelable(false)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finish();
                    }
                })
                .setPositiveButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Initialize the Universe
     */
    private void initializeUniverse() {
        while (solarSystemInteractor.getAllSolarSystems().size() < 10) {
            SolarSystem curSolarSystem = new SolarSystem();
            solarSystemViewModel.addSolarSystem(curSolarSystem);
        }
    }

    /**
     * Print the Universe
     */
    private void printUniverse() {
        for (SolarSystem s : solarSystemViewModel.getAllSolarSystems()) {
            String content = s.toString();
            largeLog("APP", content);
        }
    }

    /**
     * Print the Universe
     */
    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }

    /**
     * Setter for numberPicker
     * @param np the numberPicker
     */
    private void setNumberPicker(NumberPicker np) {
        np.setMinValue(0);
        np.setMaxValue(16);
        np.setOnValueChangedListener(onValueChangeListener);
    }


}