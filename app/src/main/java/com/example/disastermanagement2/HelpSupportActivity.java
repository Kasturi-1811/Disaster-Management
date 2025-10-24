package com.example.disastermanagement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HelpSupportActivity extends AppCompatActivity {

    Button needHelpBtn, offerHelpBtn, viewRequestsBtn, viewOffersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_support);

        needHelpBtn = findViewById(R.id.needHelpBtn);
        offerHelpBtn = findViewById(R.id.offerHelpBtn);
        viewRequestsBtn = findViewById(R.id.viewRequestsBtn);
        viewOffersBtn = findViewById(R.id.viewOffersBtn);

        needHelpBtn.setOnClickListener(v ->
                startActivity(new Intent(this, NeedHelpActivity.class))
        );

        offerHelpBtn.setOnClickListener(v ->
                startActivity(new Intent(this, OfferHelpActivity.class))
        );

        viewRequestsBtn.setOnClickListener(v ->
                startActivity(new Intent(this, ViewHelpRequestsActivity.class))
        );

        viewOffersBtn.setOnClickListener(v ->
                startActivity(new Intent(this, ViewHelpOffersActivity.class))
        );
    }
}
