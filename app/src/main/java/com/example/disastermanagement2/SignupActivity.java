package com.example.disastermanagement2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText emailEt, passwordEt, confirmEt, phoneEt;
    Button signupBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        confirmEt = findViewById(R.id.confirmEt);
        phoneEt = findViewById(R.id.phoneEt);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(v -> {
            String email = emailEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            String confirm = confirmEt.getText().toString().trim();
            String phone = phoneEt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty() || confirm.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidPassword(password)) {
                Toast.makeText(this,
                        "Password must be at least 8 characters, include uppercase, lowercase, number, and special character.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.insertUser(email, password, phone)) {
                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }
}
