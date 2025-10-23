package com.example.disastermanagement2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailEt, newPasswordEt;
    Button resetBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        db = new DatabaseHelper(this);

        emailEt = findViewById(R.id.emailEt);
        newPasswordEt = findViewById(R.id.newPasswordEt);
        resetBtn = findViewById(R.id.resetBtn);

        resetBtn.setOnClickListener(v -> {
            String email = emailEt.getText().toString().trim();
            String newPassword = newPasswordEt.getText().toString().trim();

            if (email.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidPassword(newPassword)) {
                Toast.makeText(this,
                        "Password must be at least 8 characters, include uppercase, lowercase, number, and special character.",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (db.checkEmailExists(email)) {
                boolean updated = db.updatePassword(email, newPassword);
                if (updated) {
                    Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error updating password!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Email not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }
}
