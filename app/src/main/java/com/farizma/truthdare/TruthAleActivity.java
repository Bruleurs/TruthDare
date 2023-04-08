package com.farizma.truthdare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class TruthAleActivity extends AppCompatActivity {

    private ArrayList<TruthItem> truthList;

    private Toolbar toolbar;
    private TextView textView;
    private RecyclerView.Adapter adapter;

    private Random random = new Random();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    private Button boutonretour;

    private String spicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_one_truth_or_dare);
        spicy = (String) getIntent().getSerializableExtra("spicy");

        sharedPreferences = getSharedPreferences("mySharedPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boutonretour = findViewById(R.id.button2);

        boutonretour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("spicy", spicy));
            }
        });

        truthList = new ArrayList<>();

        populateDefaultData();
        if(sharedPreferences.contains("UserTruth"))
            populateUserData(sharedPreferences.getString("UserTruth", null));
        recyclerViewConfig();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boutonretour.setEnabled(true);
    }

    public void populateDefaultData() {
        Values values = new Values();
        if(Objects.equals(spicy, "easy")) {
            for (int i = 0; i < values.truthseasy.length; i++)
                truthList.add(new TruthItem(values.truthseasy[i]));
        } else if(Objects.equals(spicy, "medium")){
            for (int i = 0; i < values.truthsmedium.length; i++)
                truthList.add(new TruthItem(values.truthsmedium[i]));
        } else{
            for (int i = 0; i < values.truthshard.length; i++)
                truthList.add(new TruthItem(values.truthshard[i]));
        }
    }

    public void populateUserData(String jsonTruth) {
        String[] dares = gson.fromJson(jsonTruth, String[].class);
        for(int i=0; i<dares.length; i++)
            truthList.add(new TruthItem(dares[i]));
    }

    public void recyclerViewConfig() {
        // config for RV
        textView = (TextView)findViewById(R.id.textView2);
        int i = random.nextInt(truthList.size());
        textView.setText(truthList.get(i).getmText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

}
