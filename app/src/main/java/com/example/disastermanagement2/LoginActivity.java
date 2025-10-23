package com.example.disastermanagement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt, passwordEt;
    private Button loginBtn;
    private TextView forgotTxt, signupTxt;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);
        forgotTxt = findViewById(R.id.forgotTxt);
        signupTxt = findViewById(R.id.signupTxt);

        // Login button click
        loginBtn.setOnClickListener(v -> {
            String email = emailEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.checkUser(email, password)) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // Pass email and password to HomeActivity via Intent
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                finish(); // close login activity
            } else {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        // Forgot password click
        forgotTxt.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Sign up click
        signupTxt.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}
