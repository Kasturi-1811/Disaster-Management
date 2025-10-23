package com.example.disastermanagement2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EarthquakeActivity extends AppCompatActivity {

    Button beforeBtn, duringBtn, afterBtn;
    TextView beforeContent, duringContent, afterContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        beforeBtn = findViewById(R.id.beforeBtn);
        duringBtn = findViewById(R.id.duringBtn);
        afterBtn = findViewById(R.id.afterBtn);

        beforeContent = findViewById(R.id.beforeContent);
        duringContent = findViewById(R.id.duringContent);
        afterContent = findViewById(R.id.afterContent);

        beforeBtn.setOnClickListener(v -> toggleSection(beforeContent, beforeBtn));
        duringBtn.setOnClickListener(v -> toggleSection(duringContent, duringBtn));
        afterBtn.setOnClickListener(v -> toggleSection(afterContent, afterBtn));
    }

    private void toggleSection(TextView content, Button button) {
        if (content.getVisibility() == View.GONE) {
            content.setVisibility(View.VISIBLE);
            button.setText(button.getText().toString().replace("+", "-"));
        } else {
            content.setVisibility(View.GONE);
            button.setText(button.getText().toString().replace("-", "+"));
        }
    }
}
