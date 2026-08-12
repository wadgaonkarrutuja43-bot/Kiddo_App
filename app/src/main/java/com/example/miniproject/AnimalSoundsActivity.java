package com.example.miniproject;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnimalSoundsActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private TextView tvTargetAnimal;
    private Button btnOpt1, btnOpt2, btnOpt3;
    private ToneGenerator toneGenerator;

    private static class AnimalItem {
        String name;
        String soundText;
        String emoji;

        AnimalItem(String name, String soundText, String emoji) {
            this.name = name;
            this.soundText = soundText;
            this.emoji = emoji;
        }
    }

    private final List<AnimalItem> animalList = new ArrayList<>();
    private AnimalItem currentTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_sounds);

        ttsHelper = new TTSHelper(this);
        toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

        tvTargetAnimal = findViewById(R.id.tvTargetAnimal);
        btnOpt1 = findViewById(R.id.btnOption1);
        btnOpt2 = findViewById(R.id.btnOption2);
        btnOpt3 = findViewById(R.id.btnOption3);

        // Pool of animals to keep rounds fresh
        animalList.add(new AnimalItem("Dog", "Woof Woof", "🐶"));
        animalList.add(new AnimalItem("Cat", "Meow Meow", "🐱"));
        animalList.add(new AnimalItem("Cow", "Moo Moo", "🐮"));
        animalList.add(new AnimalItem("Duck", "Quack Quack", "🦆"));
        animalList.add(new AnimalItem("Lion", "Roar", "🦁"));
        animalList.add(new AnimalItem("Pig", "Oink Oink", "🐷"));
        animalList.add(new AnimalItem("Frog", "Ribbit Ribbit", "🐸"));

        loadNewRound();

        btnOpt1.setOnClickListener(v -> checkAnswer((AnimalItem) btnOpt1.getTag()));
        btnOpt2.setOnClickListener(v -> checkAnswer((AnimalItem) btnOpt2.getTag()));
        btnOpt3.setOnClickListener(v -> checkAnswer((AnimalItem) btnOpt3.getTag()));
    }

    private void loadNewRound() {
        // Pick target animal randomly
        List<AnimalItem> shuffledList = new ArrayList<>(animalList);
        Collections.shuffle(shuffledList);

        currentTarget = shuffledList.get(0);
        tvTargetAnimal.setText("Tap the " + currentTarget.name + "!");
        ttsHelper.speak("Tap the " + currentTarget.name);

        // Pick 3 unique options including target
        List<AnimalItem> options = shuffledList.subList(0, 3);
        Collections.shuffle(options);

        setupButton(btnOpt1, options.get(0));
        setupButton(btnOpt2, options.get(1));
        setupButton(btnOpt3, options.get(2));
    }

    private void setupButton(Button btn, AnimalItem item) {
        btn.setText(item.emoji + " " + item.name);
        btn.setTag(item);
    }

    private void checkAnswer(AnimalItem selected) {
        if (selected.name.equals(currentTarget.name)) {
            // Success sound effect + TTS celebration
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 250);
            ttsHelper.speak("Yay! " + selected.name + " says " + selected.soundText + "! Great job!");
            btnOpt1.postDelayed(this::loadNewRound, 2000);
        } else {
            ttsHelper.speak("Oops! Try again!");
        }
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) ttsHelper.shutdown();
        if (toneGenerator != null) toneGenerator.release();
        super.onDestroy();
    }
}