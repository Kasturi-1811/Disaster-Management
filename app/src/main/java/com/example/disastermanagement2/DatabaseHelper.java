package com.example.disastermanagement2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "UserDatabase.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_PHONE = "phone";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_EMAIL + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT, " +
                COL_PHONE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(String email, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);
        values.put(COL_PHONE, phone);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email=? AND password=?",
                new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean checkEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public Cursor getUserData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email=?", new String[]{email});
    }

    public boolean updatePhone(String email, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PHONE, newPhone);
        int result = db.update(TABLE_NAME, values, COL_EMAIL + "=?", new String[]{email});
        return result > 0;
    }
    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PASSWORD, newPassword);
        int result = db.update(TABLE_NAME, values, COL_EMAIL + "=?", new String[]{email});
        return result > 0;
    }
    // --- Need Help ---
    public boolean insertHelpRequest(String name, String location, String phone, String helpType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS help_requests(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT, phone TEXT, help_type TEXT)");
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("phone", phone);
        values.put("help_type", helpType);
        long result = db.insert("help_requests", null, values);
        return result != -1;
    }

    // --- Offer Help ---
    public boolean insertHelpOffer(String name, String location, String phone, String helpType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS help_offers(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT, phone TEXT, help_type TEXT)");
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("phone", phone);
        values.put("help_type", helpType);
        long result = db.insert("help_offers", null, values);
        return result != -1;
    }

    // --- Get all requests ---
    public Cursor getAllHelpRequests() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS help_requests(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT, phone TEXT, help_type TEXT)");
        return db.rawQuery("SELECT * FROM help_requests", null);
    }

    // --- Get all offers ---
    public Cursor getAllHelpOffers() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS help_offers(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, location TEXT, phone TEXT, help_type TEXT)");
        return db.rawQuery("SELECT * FROM help_offers", null);
    }

}
