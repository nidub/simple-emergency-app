package com.appdid.emergencyservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "emergencyservice.db";
    public static final String TABLE_NAME = "emergencyservice";
    public static final String COL_1_ID = "ID";
    public static final String COL_2_NAME = "NAME";
    public static final String COL_3_EMAIL = "EMAIL";
    public static final String COL_4_PHONENUMBER = "PHONENUMBER";
    public static final String COL_5_PASSWORD = "PASSWORD";

    private String CREATE_ES_TABLE ="CREATE TABLE " + TABLE_NAME + "("
            + COL_1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2_NAME + " TEXT,"
            + COL_3_EMAIL + " TEXT,"+ COL_4_PHONENUMBER + " INTEGER," + COL_5_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void insertData(Emergencyservice emergencyservice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_NAME,emergencyservice.getName());
        contentValues.put(COL_3_EMAIL,emergencyservice.getEmail());
        contentValues.put(COL_4_PHONENUMBER,emergencyservice.getPhonenumber1());
        contentValues.put(COL_5_PASSWORD,emergencyservice.getPassword());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        db.close();
    }

    public boolean checkUser(String email) {
        String[] columns = {COL_1_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {COL_1_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3_EMAIL + " = ?" + " AND " + COL_5_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkPass(String email, String password) {
        String[] columns = {COL_1_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3_EMAIL + " = ?" + " AND " + COL_5_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5_PASSWORD, password);
        db.update(TABLE_NAME, values, COL_3_EMAIL+" = ?",new String[] { email });
        db.close();
    }
}
