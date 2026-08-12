package com.example.miniproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class color extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private RecyclerView recyclerView;

    // Define colors along with their corresponding hex values
    private final String[][] colorData = {
            {"BLUE", "#0000FF"},
            {"RED", "#FF0000"},
            {"YELLOW", "#FFEB3B"},
            {"ORANGE", "#FF9800"},
            {"GREEN", "#4CAF50"},
            {"PURPLE", "#9C27B0"},
            {"PINK", "#E91E63"},
            {"BLACK", "#000000"},
            {"WHITE", "#FFFFFF"},
            {"BROWN", "#795548"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        // Set Title
        TextView title = findViewById(R.id.titleText);
        if (title != null) {
            title.setText("COLOURS");
        }

        recyclerView = findViewById(R.id.recyclerView);

        // Build list of LearningItems using hex codes for LearningAdapter circle rendering
        List<LearningItem> colorList = new ArrayList<>();
        for (String[] colorInfo : colorData) {
            String name = colorInfo[0];
            String hex = colorInfo[1];

            // Pass Hex code as 3rd parameter so LearningAdapter detects '#' and tints circle_shape
            colorList.add(new LearningItem(name, 0, hex));
        }

        setupResponsiveGrid(recyclerView, colorList);
    }

    private void setupResponsiveGrid(RecyclerView recyclerView, List<LearningItem> items) {
        float screenWidthDp = getResources().getDisplayMetrics().widthPixels /
                getResources().getDisplayMetrics().density;

        // Calculates column count dynamically for phones and tablets
        int spanCount = Math.max(2, (int) (screenWidthDp / 140));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        LearningAdapter adapter = new LearningAdapter(items, ttsHelper);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}