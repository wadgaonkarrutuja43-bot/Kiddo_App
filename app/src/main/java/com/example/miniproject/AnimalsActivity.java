package com.example.miniproject;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AnimalsActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private final int[] animalImages = {
            R.drawable.lion, R.drawable.elephant, R.drawable.tiger,
            R.drawable.monkey, R.drawable.cow, R.drawable.dog,
            R.drawable.cat, R.drawable.sheep,
            R.drawable.horse, R.drawable.pig
    };

    private final String[] animalNames = {
            "Lion", "Elephant", "Tiger",
            "Monkey", "Cow", "Dog",
            "Cat", "Sheep",
            "Horse", "Pig"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        // Initialize TTS with slower speed and higher pitch
        ttsHelper = new TTSHelper(this);

        GridView gridView = findViewById(R.id.gridAnimals);
        AnimalAdapter adapter = new AnimalAdapter(this, animalImages, animalNames);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Speak animal name slowly using TTSHelper
            if (ttsHelper != null) {
                ttsHelper.speakSlowly(animalNames[position]);
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