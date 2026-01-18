package com.example.miniproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity2 extends AppCompatActivity {

    int flag = 0;
    int count = 0;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game2);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

        public void check (View view){
            Button btnCurrent = (Button) view;

            if (!btnCurrent.getText().toString().equals("")) {
                return; // Already clicked
            }

            count++;

            if (flag == 0) {
                btnCurrent.setText("X");
                flag = 1;
            } else {
                btnCurrent.setText("O");
                flag = 0;
            }

            String b1 = btn1.getText().toString();
            String b2 = btn2.getText().toString();
            String b3 = btn3.getText().toString();
            String b4 = btn4.getText().toString();
            String b5 = btn5.getText().toString();
            String b6 = btn6.getText().toString();
            String b7 = btn7.getText().toString();
            String b8 = btn8.getText().toString();
            String b9 = btn9.getText().toString();

            if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                showWinner(b1);
            } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                showWinner(b4);
            } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                showWinner(b7);
            } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                showWinner(b1);
            } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                showWinner(b2);
            } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                showWinner(b3);
            } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
                showWinner(b1);
            } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                showWinner(b3);
            } else if (count == 9) {
                Toast.makeText(this, "Match is Drawn", Toast.LENGTH_LONG).show();
                newGame();
            }
        }

    private void showWinner(String winner) {
        Toast.makeText(this, "Winner is: " + winner, Toast.LENGTH_LONG).show();
        newGame();
    }

    private void newGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        flag = 0;
        count = 0;
    }
}