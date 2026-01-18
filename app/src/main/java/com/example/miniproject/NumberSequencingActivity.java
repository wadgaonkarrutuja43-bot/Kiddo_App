package com.example.miniproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumberSequencingActivity extends AppCompatActivity {

    private TextView questionText;
    private ProgressBar progressBar;
    private Button btnOption1, btnOption2;
    private ImageView image1, image2, image3;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private Random random;

    // Sample Questions with sequences (e.g., the correct number is the next in the sequence)
    private int[][] questions = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {10, 11, 12, 13 },
            {4, 5, 6, 7},
            {9, 10, 11, 12}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_sequence);

        questionText = findViewById(R.id.questionText);
        progressBar = findViewById(R.id.progressBar);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        random = new Random();

        loadNextQuestion();

        btnOption1.setOnClickListener(v -> checkAnswer(btnOption1.getText().toString()));
        btnOption2.setOnClickListener(v -> checkAnswer(btnOption2.getText().toString()));
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex >= questions.length) {
            // End of game dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over!");
            builder.setMessage("Your Score: " + score + "/" + questions.length);
            builder.setPositiveButton("OK", (dialog, which) -> finish());
            builder.setCancelable(false);
            builder.show();
            return;
        }

        int[] question = questions[currentQuestionIndex];
        questionText.setText("Which number is next?");

        // Set the numbers in the sequence images
        image1.setImageResource(getNumberImage(question[0]));
        image2.setImageResource(getNumberImage(question[1]));
        image3.setImageResource(getNumberImage(question[2]));

        // Set the options randomly
        int correctAnswer = question[3];
        if (random.nextBoolean()) {
            btnOption1.setText(String.valueOf(correctAnswer));
            btnOption2.setText(String.valueOf(correctAnswer + random.nextInt(5) + 1));
        } else {
            btnOption2.setText(String.valueOf(correctAnswer));
            btnOption1.setText(String.valueOf(correctAnswer + random.nextInt(5) + 1));
        }

        // Update progress
        progressBar.setProgress((currentQuestionIndex + 1) * (100 / questions.length));
    }

    private void checkAnswer(String selectedOption) {
        int[] question = questions[currentQuestionIndex];
        int correctAnswer = question[3];
        int selectedAnswer = Integer.parseInt(selectedOption);

        if (selectedAnswer == correctAnswer) {
            score++;
            showMessage("Correct!", true);
        } else {
            showMessage("Try Again!", false);
        }
    }

    private void showMessage(String message, boolean isCorrect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(message);

        if (isCorrect) {
            builder.setPositiveButton("Next", (dialog, which) -> {
                currentQuestionIndex++;
                loadNextQuestion();
            });
        } else {
            builder.setPositiveButton("OK", null);
        }

        builder.show();
    }

    private int getNumberImage(int number) {
        switch (number) {
            case 1:
                return R.drawable.number1;
            case 2:
                return R.drawable.number2;
            case 3:
                return R.drawable.number3;
            case 4:
                return R.drawable.number4;
            case 5:
                return R.drawable.number5;
            case 6:
                return R.drawable.number6;
            case 7:
                return R.drawable.number7;
            case 8:
                return R.drawable.number8;
            case 9:
                return R.drawable.number9;
            case 10:
                return R.drawable.number10;
            case 11:
                return R.drawable.number11;
            case 12:
                return R.drawable.number12;
            case 13:
                return R.drawable.number13;
            case 14:
                return R.drawable.number12;
            default:
                return R.drawable.number_default;
        }
    }
}
