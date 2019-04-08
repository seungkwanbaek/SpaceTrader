package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Difficulty;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.Ship;
import com.example.spacetrader.entities.ShipType;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.model.Model;
import com.example.spacetrader.model.SolarSystemInteractor;
import com.example.spacetrader.viewmodel.PlayerViewModel;
import com.example.spacetrader.viewmodel.SolarSystemViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



public class MainActivity extends AppCompatActivity implements ValueEventListener {
    public static final String PLAYER_NAME = "PLAYER_NAME";

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
    final int skillPointsMax = 16;
    static private int maxLogLength = 4000;

    private SolarSystemInteractor solarSystemInteractor = Model.getInstance()
            .getSolarSystemInteractor();
    private static final String TAG = "MainActivity";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference ref = database.getReference();

    public static final DatabaseReference myPlayerReference = ref.child("player");
    private Player lastPlayer;
    public static final DatabaseReference mySolarSystemReference = ref.child("ss");
    public static final DatabaseReference myResourceReference = ref.child("resource");

    /** Value Change Listener for NumberPicker */
    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(MainActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    /**
     * Setup the initial page, let the user create a new player or load previous saved player
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        solarSystemViewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        String curName = "";

        nameField = findViewById(R.id.username_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        pPoint = findViewById(R.id.pilot_point);
        fPoint = findViewById(R.id.fighter_point);
        tPoint = findViewById(R.id.trader_point);
        ePoint = findViewById(R.id.engineer_point);

        setNumberPicker(pPoint);
        setNumberPicker(fPoint);
        setNumberPicker(tPoint);
        setNumberPicker(ePoint);
        initializeUniverse();
        printUniverse();
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
        } else //noinspection MagicNumber,MagicNumber,MagicNumber,MagicNumber
            if (skillPointsSum > skillPointsMax) {
            String res = "Skill points cannot exceed 16!";
            Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
        } else if (skillPointsSum < skillPointsMax) {
            String res = "Please allocate all skill points!";
            Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
        } else {
            SolarSystem selectedSolarSystem = solarSystemViewModel.getAllSolarSystems().get(0);
            final Player player = new Player(pName, pDifficulty,
                    new ArrayList<>((Arrays.asList(pValue, fValue, tValue, eValue))),
                    selectedSolarSystem);
            playerViewModel.addPlayer(player);
            // write into database
            myPlayerReference.setValue(player);
            mySolarSystemReference.setValue(solarSystemViewModel.getAllSolarSystems());
            // go to player page
            Intent intent = new Intent(MainActivity.this, ShowPlayerActivity.class);
            intent.putExtra(PLAYER_NAME, pName);
            startActivity(intent);
        }
    }

    /**
     * Button handler for the HISTORY button
     * @param view the button was pressed
     */
    public void onLastPressed(View view) {
        initializeUniverse();
        printUniverse();
        solarSystemViewModel.addSolarSystem(lastPlayer.getSolarSystem());
        playerViewModel.addPlayer(lastPlayer);
        mySolarSystemReference.setValue(solarSystemViewModel.getAllSolarSystems());
        Intent intent = new Intent(MainActivity.this, ShowPlayerActivity.class);
        intent.putExtra(PLAYER_NAME, lastPlayer.getUserName());
        startActivity(intent);
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
     * Listener that save changes to firebase
     * @param dataSnapshot
     */
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            System.out.println(dataSnapshot.getValue().toString());
            String userName = dataSnapshot.child("userName").getValue(String.class);
            String difficulty = dataSnapshot.child("difficulty").getValue(String.class);
            int currentCredit = dataSnapshot.child("currentCredit").getValue(Integer.class);
            // skillPoints
            ArrayList<Integer> skillPoints = new ArrayList<>();
            int ePoint = dataSnapshot.child("skillPoints").child("Engineer").getValue(Integer.class);
            int fPoint = dataSnapshot.child("skillPoints").child("Fighter").getValue(Integer.class);
            int pPoint = dataSnapshot.child("skillPoints").child("Pilot").getValue(Integer.class);
            int tPoint = dataSnapshot.child("skillPoints").child("Trader").getValue(Integer.class);
            skillPoints.add(pPoint);
            skillPoints.add(fPoint);
            skillPoints.add(tPoint);
            skillPoints.add(ePoint);
            System.out.println("Engineer: " + ePoint + ", Fighter: " + fPoint + ", pPoint: "
                    + pPoint + ", Trader: " + tPoint);
            // ship
            String shipType1 = dataSnapshot.child("ship").child("type").getValue(String.class);
            ShipType shipType;
            if (shipType1.equals("Gnat")) {
                shipType = ShipType.Gnat;
            } else {
                shipType = ShipType.Flea;
            }
            HashMap<String, Long> cargo = new HashMap<>();
            if (dataSnapshot.child("ship").child("cargo").getValue() != null) {
                cargo = (HashMap<String, Long>) dataSnapshot.child("ship").child("cargo").getValue();
            }
            double fuelAmount = dataSnapshot.child("ship").child("fuelAmount").getValue(Double.class);
            Ship ship = new Ship(shipType, cargo, fuelAmount);
            // solarSystem
            String solarSystemName = dataSnapshot.child("solarSystem").child("name")
                    .getValue(String.class);
            String resourceDescrption = dataSnapshot.child("solarSystem").child("resourceDescrption")
                    .getValue(String.class);
            String techLevel = dataSnapshot.child("solarSystem").child("techLevel").getValue(String.class);
            int x = dataSnapshot.child("solarSystem").child("x").getValue(Integer.class);
            int y = dataSnapshot.child("solarSystem").child("y").getValue(Integer.class);
            SolarSystem solarSystem = new SolarSystem(solarSystemName, resourceDescrption, techLevel, x, y);
            lastPlayer = new Player(userName, difficulty, skillPoints, ship, solarSystem, currentCredit);
            playerViewModel.addPlayer(lastPlayer);
        }
    }

    /**
     * Handle error of firebase
     * @param databaseError
     */
    @Override
    public void onCancelled(DatabaseError databaseError) {
        System.out.println("The read failed: " + databaseError.getCode());
    }

    /**
     * Start firebase
     */
    protected void onStart() {
        super.onStart();
        myPlayerReference.addListenerForSingleValueEvent(this);
    }

    /**
     * Initialize the Universe
     */
    private void initializeUniverse() {
        int i = 0;
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
        if (content.length() > maxLogLength) {
            Log.d(tag, content.substring(0, maxLogLength));
            largeLog(tag, content.substring(maxLogLength));
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
        np.setMaxValue(skillPointsMax);
        np.setOnValueChangedListener(onValueChangeListener);
    }

}