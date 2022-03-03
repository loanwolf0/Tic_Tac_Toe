package com.example.tictoe;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {

    private static final String TAG = "hlw";
    mydatabase my_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Cursor cursor = my_database.viewData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data Found ", Toast.LENGTH_SHORT).show();
        }

        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {
            stringBuffer.append( cursor.getString(0) + "\n");
            stringBuffer.append("Date :- " + cursor.getString(2) + "\n");
            stringBuffer.append("Winner Name :- " + cursor.getString(2) + "\n");
            stringBuffer.append("Opponent was :- " + cursor.getString(3) + "\n");
        }
        Log.d(TAG, "onCreate: "+stringBuffer);

//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setMessage(""+stringBuffer);
//        alert.create().show();

    }



}