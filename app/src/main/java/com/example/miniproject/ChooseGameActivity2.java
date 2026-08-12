package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseGameActivity2 extends AppCompatActivity {

    private TTSHelper ttsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_game2);

        ttsHelper = new TTSHelper(this);

        // 1. Animal Sounds Game
        findViewById(R.id.btnAnimalSounds).setOnClickListener(v -> {
            ttsHelper.speak("Animal Sounds");
            Intent intent = new Intent(ChooseGameActivity2.this, AnimalSoundsActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // 2. Shape Tap Game
        findViewById(R.id.btnShapeTap).setOnClickListener(v -> {
            ttsHelper.speak("Shape Tap");
            Intent intent = new Intent(ChooseGameActivity2.this, ShapeTapActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // 3. Balloon Pop Game
        findViewById(R.id.btnBalloonGame).setOnClickListener(v -> {
            ttsHelper.speak("Balloon Pop Game");
            Intent intent = new Intent(ChooseGameActivity2.this, BalloonGameActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // 4. Coloring Canvas
        findViewById(R.id.btnColoringGame).setOnClickListener(v -> {
            ttsHelper.speak("Coloring Canvas");
            Intent intent = new Intent(ChooseGameActivity2.this, ColoringGameActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // 5. Drag & Drop Matching Game
        findViewById(R.id.btnMatchingGame).setOnClickListener(v -> {
            ttsHelper.speak("Matching Game");
            Intent intent = new Intent(ChooseGameActivity2.this, MatchingGameActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // 6. Flashcards
        findViewById(R.id.btnFlashcards).setOnClickListener(v -> {
            ttsHelper.speak("Flashcards");
            Intent intent = new Intent(ChooseGameActivity2.this, FlashcardActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}