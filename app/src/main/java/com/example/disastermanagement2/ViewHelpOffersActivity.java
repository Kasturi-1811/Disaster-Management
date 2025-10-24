package com.example.disastermanagement2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewHelpOffersActivity extends AppCompatActivity {

    DatabaseHelper db;
    LinearLayout listLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_help_offers);

        db = new DatabaseHelper(this);
        listLayout = findViewById(R.id.listLayout);

        Cursor cursor = db.getAllHelpOffers();
        if (cursor.getCount() == 0) {
            TextView empty = new TextView(this);
            empty.setText("No help offers available right now.");
            listLayout.addView(empty);
            return;
        }

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String location = cursor.getString(2);
            String phone = cursor.getString(3);
            String help = cursor.getString(4);

            TextView tv = new TextView(this);
            tv.setText("🤝 " + name + "\n📍 " + location + "\n📞 " + phone + "\n💬 " + help);
            tv.setPadding(16, 16, 16, 16);
            tv.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
            tv.setTextSize(16);
            listLayout.addView(tv);
        }
        cursor.close();
    }
}
