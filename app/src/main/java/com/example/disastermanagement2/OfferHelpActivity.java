package com.example.disastermanagement2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OfferHelpActivity extends AppCompatActivity {

    EditText nameEt, locationEt, phoneEt, helpTypeEt;
    Button submitBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_help);

        db = new DatabaseHelper(this);

        nameEt = findViewById(R.id.nameEt);
        locationEt = findViewById(R.id.locationEt);
        phoneEt = findViewById(R.id.phoneEt);
        helpTypeEt = findViewById(R.id.helpTypeEt);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(v -> {
            String name = nameEt.getText().toString().trim();
            String location = locationEt.getText().toString().trim();
            String phone = phoneEt.getText().toString().trim();
            String helpType = helpTypeEt.getText().toString().trim();

            if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || helpType.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = db.insertHelpOffer(name, location, phone, helpType);
            if (inserted) {
                Toast.makeText(this, "Help offer submitted!", Toast.LENGTH_SHORT).show();
                finish(); // go back to HelpSupportActivity
            } else {
                Toast.makeText(this, "Error saving offer!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
