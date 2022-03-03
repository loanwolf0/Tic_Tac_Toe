package com.example.tictoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class mydatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final String DATATABLE_NAME = "dataTable";
    private static final String TAG = "hi";


    public mydatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE DATATABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT , date text , winnerName text , opponentName text )";
        sqLiteDatabase.execSQL(query);
        Log.d(TAG, "mydatabase: " + query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String date, String winner_name, String opponent_name) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("winnerName", winner_name);
        contentValues.put("opponentName", opponent_name);

        long results = sqLiteDatabase.insert("DATATABLE_NAME", null, contentValues);

        if (results == -1) {
            return false;
        }
        return true ;
    }


    public Cursor viewData (){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DATATABLE_NAME",null);
        return cursor;
    }
}
