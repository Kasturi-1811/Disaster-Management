package com.example.disastermanagement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DisasterPreparednessActivity extends AppCompatActivity {

    Button fireBtn, floodBtn, earthquakeBtn, cycloneBtn, rainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disaster_preparedness);

        fireBtn = findViewById(R.id.fireBtn);
        floodBtn = findViewById(R.id.floodBtn);
        earthquakeBtn = findViewById(R.id.earthquakeBtn);
        cycloneBtn = findViewById(R.id.cycloneBtn);
        rainBtn = findViewById(R.id.rainBtn);

        fireBtn.setOnClickListener(v -> startActivity(new Intent(this, FireActivity.class)));
        floodBtn.setOnClickListener(v -> startActivity(new Intent(this, FloodActivity.class)));
        earthquakeBtn.setOnClickListener(v -> startActivity(new Intent(this, EarthquakeActivity.class)));
        cycloneBtn.setOnClickListener(v -> startActivity(new Intent(this, CycloneActivity.class)));
        rainBtn.setOnClickListener(v -> startActivity(new Intent(this, RainActivity.class)));
    }
}
