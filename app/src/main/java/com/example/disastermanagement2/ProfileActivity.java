package com.example.disastermanagement2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView emailText, passwordText;
    private EditText phoneEdit;
    private Button editPhoneBtn, saveBtn, signOutBtn;
    private DatabaseHelper db;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        phoneEdit = findViewById(R.id.phoneEdit);
        editPhoneBtn = findViewById(R.id.editPhoneBtn);
        saveBtn = findViewById(R.id.saveBtn);
        signOutBtn = findViewById(R.id.signOutBtn);
        db = new DatabaseHelper(this);

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        emailText.setText("Email: " + email);
        passwordText.setText("Password: " + "*".repeat(password.length()));

        // Load phone from DB
        Cursor cursor = db.getUserData(email);
        if (cursor.moveToFirst()) {
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            phoneEdit.setText(phone);
        }
        cursor.close();

        phoneEdit.setEnabled(false);

        editPhoneBtn.setOnClickListener(v -> {
            phoneEdit.setEnabled(true);
            phoneEdit.requestFocus();
        });

        saveBtn.setOnClickListener(v -> {
            String newPhone = phoneEdit.getText().toString();
            if (db.updatePhone(email, newPhone)) {
                Toast.makeText(this, "Phone updated", Toast.LENGTH_SHORT).show();
                phoneEdit.setEnabled(false);
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        signOutBtn.setOnClickListener(v -> finish());
    }
}
