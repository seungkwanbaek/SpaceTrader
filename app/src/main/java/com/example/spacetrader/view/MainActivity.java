package com.example.spacetrader.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Difficulty;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.AddPlayerViewModel;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.androidtutorial.MESSAGE";
    final Context context = this;
    private Spinner difficultySpinner;
    private EditText nameField;

    private Player player;
    private AddPlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.student_name_input);

        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        /** Set the numberPicker for skillPoints */
        final NumberPicker pPoint = findViewById(R.id.pilot_point);
        final NumberPicker fPoint = findViewById(R.id.fighter_point);
        final NumberPicker tPoint = findViewById(R.id.trader_point);
        final NumberPicker ePoint = findViewById(R.id.engineer_point);

        setNumberPicker(pPoint);
        setNumberPicker(fPoint);
        setNumberPicker(tPoint);
        setNumberPicker(ePoint);

        /** Set the save button */
        Button saveButton = findViewById(R.id.save_button);

        /** Called when the user taps the Save button */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pValue = pPoint.getValue();
                int fValue = fPoint.getValue();
                int tValue = tPoint.getValue();
                int eValue = ePoint.getValue();

                int skillPointsSum = pValue + fValue + tValue + eValue;

                if (skillPointsSum > 16) {
                    String res = "Skill points cannot exceed 16!";
                    Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
                } else if (skillPointsSum == 0) {
                    String res = "Please allocate the skill points!";
                    Toast.makeText(MainActivity.this, "Warning: " + res, Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ShowPlayerActivity.class);
                    EditText editText = findViewById(R.id.student_name_input);
                    String message = editText.getText().toString();
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }

            }
        });

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

    /**
     * Value Change Listener for NumberPicker
     */
    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(MainActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };
}