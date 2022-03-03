package com.example.tictoe;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class game_part_1 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "xx";
    mydatabase mydatabase;
    String Winner, Opponent;
    int winner = 0;
    int total_matches = 0 ;
    int tie_matches = 0;
    LinearLayout players_Id, winner_Win;
    ImageButton refresh;

    TextView t1, t2, playerTurn, player_x_score, player_o_score, tic_Tac_Toe, winner_Name, powered_by_Anurag_shukla , tie_Matches, total_Matches;
    FloatingActionButton show_history;
    int player_x = 0;
    int times_wins_x = 0;
    int times_wins_o = 0;
    boolean isWin = false;
    int player_o = 1;
    int pTurn = player_x;
    int[] field = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8;
    boolean activeGame = true;
    String massage_x, massage_o;
    Button btn;
    int count = 9;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_part1);

        mydatabase = new mydatabase(this);

        t1 = findViewById(R.id.player_x_name);
        t2 = findViewById(R.id.player_o_name);
        tie_Matches = findViewById(R.id.tie_matches);
        total_Matches = findViewById(R.id.total_matches);
        player_x_score = findViewById(R.id.player_x_score);
        player_o_score = findViewById(R.id.player_o_score);
        playerTurn = findViewById(R.id.player_turn);
        winner_Name = findViewById(R.id.winner_name);
        powered_by_Anurag_shukla = findViewById(R.id.powered_by_Anurag_shukla);
        show_history = findViewById(R.id.show_history);
        winner_Win = findViewById(R.id.winner_win);
        tic_Tac_Toe = findViewById(R.id.tic_tac_toe);
        players_Id = findViewById(R.id.players_id);
        refresh = findViewById(R.id.refresh);

        b0 = findViewById(R.id.btn1);
        b1 = findViewById(R.id.btn2);
        b2 = findViewById(R.id.btn3);
        b3 = findViewById(R.id.btn4);
        b4 = findViewById(R.id.btn5);
        b5 = findViewById(R.id.btn6);
        b6 = findViewById(R.id.btn7);
        b7 = findViewById(R.id.btn8);
        b8 = findViewById(R.id.btn9);


        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);

        massage_x = getIntent().getStringExtra("name_x");
        massage_o = getIntent().getStringExtra("name_o");
        t1.setText(massage_x);
        t2.setText(massage_o);
        playerTurn.setText(massage_x + "  turn");

    }

    @SuppressLint("ResourceAsColor")
    private void resetEveryThings() {
        pTurn = player_x;
        field = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        winner = 0;

        b0.setText("");
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");

//        b0.setBackgroundColor(R.drawable.layout_4);
//        b1.setBackgroundColor(R.drawable.layout_4);
//        b2.setBackgroundColor(R.drawable.layout_4);
//        b3.setBackgroundColor(Color.parseColor("#C4C2C1"));
//        b4.setBackgroundColor(Color.parseColor("#C4C2C1"));
//        b5.setBackgroundColor(Color.parseColor("#C4C2C1"));
//        b6.setBackgroundColor(Color.parseColor("#C4C2C1"));
//        b7.setBackgroundColor(Color.parseColor("#C4C2C1"));
//        b8.setBackgroundColor(Color.parseColor("#C4C2C1"));

        playerTurn.setText(massage_x + "  turn");
        isWin = false;
        count = 9;
        activeGame = true;

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {

        btn = findViewById(view.getId());
        int tag = Integer.parseInt(btn.getTag().toString());
        count--;

        if (field[tag] != -1) {
            return;
        }
        if (!activeGame) {
            return;
        }

        if (pTurn == player_x) {
            btn.setText("X");
            btn.setTextColor(R.color.player_x);
           // btn.setBackgroundColor(Color.parseColor("#FF9100"));
            //btn.setBackgroundColor(R.drawable.layout_5);
            pTurn = player_o;
            field[tag] = player_x;
            playerTurn.setText(massage_o + "  turn");

        } else {
            btn.setText("O");
            btn.setTextColor(R.color.player_o);
            //btn.setBackgroundColor(Color.parseColor("#FFDA38"));
            pTurn = player_x;
            field[tag] = player_o;
            playerTurn.setText(massage_x + "  turn");
        }

        checkWinner();

        if (!isWin && count == 0) {
            show("Tie", " Nobody is winner ");
        }
    }

    public void checkWinner() {
        int[][] winnerList = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int x = winnerList[i][0];
            int y = winnerList[i][1];
            int z = winnerList[i][2];

            if (field[x] == field[y] && field[y] == field[z] && field[x] == player_x) {
                times_wins_x++;
                total_matches++;
                total_Matches.setText(""+total_matches);
                player_x_score.setText("" + times_wins_x);
                activeGame = false;
                isWin = true;
                winner = 1;
                winner(massage_x);
            }
            if (field[x] == field[y] && field[y] == field[z] && field[x] == player_o) {
                times_wins_o++;
                total_matches++;
                total_Matches.setText(""+total_matches);
                player_o_score.setText("" + times_wins_o);
                activeGame = false;
                winner = 2;
                isWin = true;
                winner(massage_o);
            }
        }
    }

    public void show(String name, String massage) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(name);
        dialog.setMessage(massage);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //insert_data();
                resetEveryThings();
                tie_matches++;
                total_matches++;
                total_Matches.setText(""+total_matches);
                tie_Matches.setText(""+tie_matches);

            }
        }).create().show();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder back = new AlertDialog.Builder(this);
        back.setTitle("Quit");
        back.setMessage(" Are yoy sure ?");
        back.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        back.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).create().show();
    }


    public void showHistory(View view) {


        Cursor cursor = mydatabase.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Found ", Toast.LENGTH_SHORT).show();
        }

        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {

            stringBuffer.append(cursor.getString(0) + ". " + "  Date :- " + cursor.getString(1) + "\n");
            stringBuffer.append("       Winner was :- " + cursor.getString(2) + "\n");
            stringBuffer.append("       Opponent was :- " + cursor.getString(3) + "\n");
            stringBuffer.append("\n");

        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("" + stringBuffer);
        alert.create().show();

    }

    public void insert_data() {
        Calendar mCalender = Calendar.getInstance();

        if (winner == 1) {
            Winner = massage_x;
            Opponent = massage_o;
        }
        if (winner == 2) {
            Winner = massage_o;
            Opponent = massage_x;
        }
        Toast.makeText(this, "" + Winner + "//" + Opponent, Toast.LENGTH_SHORT).show();
        String date = String.valueOf(mCalender.get(Calendar.DAY_OF_MONTH)) + " / "
                + String.valueOf(mCalender.get(Calendar.MONTH) + 1) + " / "
                + String.valueOf(mCalender.get(Calendar.YEAR));

        boolean data_Exits = mydatabase.insertData(date, Winner, Opponent);

        if (data_Exits) {
            Toast.makeText(this, "successfully data added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "data not added ", Toast.LENGTH_SHORT).show();
        }
    }

    public void winner(String winnerName) {
        tic_Tac_Toe.setVisibility(View.INVISIBLE);
        playerTurn.setVisibility(View.INVISIBLE);
        players_Id.setVisibility(View.INVISIBLE);
        show_history.setVisibility(View.INVISIBLE);
        powered_by_Anurag_shukla.setVisibility(View.INVISIBLE);
        winner_Win.setVisibility(View.VISIBLE);
        refresh.setVisibility(View.VISIBLE);
        winner_Name.setText(winnerName);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEveryThings();
                tic_Tac_Toe.setVisibility(View.VISIBLE);
                playerTurn.setVisibility(View.VISIBLE);
                players_Id.setVisibility(View.VISIBLE);
                show_history.setVisibility(View.VISIBLE);
                powered_by_Anurag_shukla.setVisibility(View.VISIBLE);
                winner_Win.setVisibility(View.INVISIBLE);
                refresh.setVisibility(View.INVISIBLE);
            }
        });


    }
}
