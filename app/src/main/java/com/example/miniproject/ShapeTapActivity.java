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

public class ShapeTapActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private TextView tvTargetShape;
    private Button btnOpt1, btnOpt2, btnOpt3;
    private ToneGenerator toneGenerator;

    private static class ShapeItem {
        String name;
        String emoji;

        ShapeItem(String name, String emoji) {
            this.name = name;
            this.emoji = emoji;
        }
    }

    private final List<ShapeItem> shapeList = new ArrayList<>();
    private ShapeItem currentTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_tap);

        ttsHelper = new TTSHelper(this);
        toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

        tvTargetShape = findViewById(R.id.tvTargetShape);
        btnOpt1 = findViewById(R.id.btnOption1);
        btnOpt2 = findViewById(R.id.btnOption2);
        btnOpt3 = findViewById(R.id.btnOption3);

        // Pool of shape items
        shapeList.add(new ShapeItem("Circle", "🔴"));
        shapeList.add(new ShapeItem("Square", "🟦"));
        shapeList.add(new ShapeItem("Triangle", "🔺"));
        shapeList.add(new ShapeItem("Star", "⭐"));
        shapeList.add(new ShapeItem("Heart", "❤️"));
        shapeList.add(new ShapeItem("Diamond", "🔷"));

        loadNewRound();

        btnOpt1.setOnClickListener(v -> checkAnswer((ShapeItem) btnOpt1.getTag()));
        btnOpt2.setOnClickListener(v -> checkAnswer((ShapeItem) btnOpt2.getTag()));
        btnOpt3.setOnClickListener(v -> checkAnswer((ShapeItem) btnOpt3.getTag()));
    }

    private void loadNewRound() {
        List<ShapeItem> shuffledList = new ArrayList<>(shapeList);
        Collections.shuffle(shuffledList);

        currentTarget = shuffledList.get(0);
        tvTargetShape.setText("Find the " + currentTarget.name + "!");
        ttsHelper.speak("Find the " + currentTarget.name);

        List<ShapeItem> options = shuffledList.subList(0, 3);
        Collections.shuffle(options);

        setupButton(btnOpt1, options.get(0));
        setupButton(btnOpt2, options.get(1));
        setupButton(btnOpt3, options.get(2));
    }

    private void setupButton(Button btn, ShapeItem item) {
        btn.setText(item.emoji + " " + item.name);
        btn.setTag(item);
    }

    private void checkAnswer(ShapeItem selected) {
        if (selected.name.equals(currentTarget.name)) {
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 250);
            ttsHelper.speak("Awesome job! You found the " + selected.name + "!");
            btnOpt1.postDelayed(this::loadNewRound, 2000);
        } else {
            ttsHelper.speak("Not quite! Try again!");
        }
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) ttsHelper.shutdown();
        if (toneGenerator != null) toneGenerator.release();
        super.onDestroy();
    }
}