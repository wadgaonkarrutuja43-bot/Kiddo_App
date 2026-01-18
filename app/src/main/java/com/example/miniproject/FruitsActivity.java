package com.example.miniproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech; // Add this import
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale; // Add this import

public class FruitsActivity extends AppCompatActivity {

    private TTSHelper ttsHelper; // Add this field
    private final int[] fruitImages = {  R.drawable.apple, R.drawable.mango, R.drawable.chikoo,
            R.drawable.pineapple, R.drawable.cherry, R.drawable.guava,
            R.drawable.banana,  R.drawable.strawberry,
            R.drawable.watermelon, R.drawable.papaya, R.drawable.orange, R.drawable.grapes, };
    private final String[] fruitNames = {  "Apple", "Mango", "Chikoo",
            "Pineapple", "Cherry", "Guava",
            "Banana", "Strawberry",
            "Watermelon", "Papaya", "Orange", "Grapes" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        GridView gridView = findViewById(R.id.gridFruits);
        FruitAdapter adapter = new FruitAdapter(this, fruitImages, fruitNames);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Speak fruit name when clicked
            if (ttsHelper != null) {
                ttsHelper.speakSlowly(fruitNames[position]);
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Clean up TTS resources
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}