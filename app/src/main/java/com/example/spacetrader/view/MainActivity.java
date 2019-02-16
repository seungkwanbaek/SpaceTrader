package com.example.spacetrader.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.spacetrader.R;
import com.example.spacetrader.entities.Difficulty;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.viewmodel.AddPlayerViewModel;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.androidtutorial.MESSAGE";
    private Spinner difficultySpinner;
    private Player player;
    private AddPlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficultySpinner = findViewById(R.id.difficulty_spinner);
        ArrayAdapter<Difficulty> difficultyArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyArrayAdapter);
    }

    /** Called when the user taps the Send button */
    public void onStartPressed(View view) {
        Intent intent = new Intent(this, ShowPlayerActivity.class);
        EditText editText = (EditText) findViewById(R.id.editPlayerName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }
}