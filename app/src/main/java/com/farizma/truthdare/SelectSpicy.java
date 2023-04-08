package com.farizma.truthdare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectSpicy extends AppCompatActivity {
    private Button boutonEasy;

    private Button boutonMedium;

    private Button boutonHard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_spicy);

        boutonEasy = findViewById(R.id.button3);

        boutonMedium = findViewById(R.id.button4);

        boutonHard = findViewById(R.id.button5);

        boutonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getApplicationContext(), MainActivity.class)).putExtra("spicy", "easy"));
            }
        });

        boutonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getApplicationContext(), MainActivity.class)).putExtra("spicy", "medium"));
            }
        });

        boutonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getApplicationContext(), MainActivity.class)).putExtra("spicy", "hard"));
            }
        });
    }
}
