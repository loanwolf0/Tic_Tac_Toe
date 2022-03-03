package com.example.tictoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText player_x_name, player_o_name;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player_x_name = findViewById(R.id.player_x_name);
        player_o_name = findViewById(R.id.player_o_name);

    }

    public void play(View view) {

        if (TextUtils.isEmpty(player_x_name.getText().toString())) {
            Toast.makeText(this, "enter the player x name ", Toast.LENGTH_SHORT).show();
            return;

        } else if (TextUtils.isEmpty(player_o_name.getText().toString())) {
            Toast.makeText(this, "enter the player o name ", Toast.LENGTH_SHORT).show();
            return;

        } else {
            Intent intent = new Intent(MainActivity.this, game_part_1.class);
            intent.putExtra("name_x", player_x_name.getText().toString());
            intent.putExtra("name_o", player_o_name.getText().toString());
            startActivity(intent);
            finish();

        }

    }

}