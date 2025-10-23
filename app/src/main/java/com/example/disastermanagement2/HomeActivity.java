package com.example.disastermanagement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome to Disaster Management App!");

        ImageView profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
        });

        Button disasterBtn = findViewById(R.id.disasterBtn);
        disasterBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, DisasterPreparednessActivity.class)));

        Button helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, HelpSupportActivity.class)));
    }
}
