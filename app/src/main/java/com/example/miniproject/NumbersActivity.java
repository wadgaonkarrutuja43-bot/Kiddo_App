package com.example.miniproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        // Set Title
        TextView title = findViewById(R.id.titleText);
        if (title != null) {
            title.setText("NUMBERS");
        }

        recyclerView = findViewById(R.id.recyclerView);

        // Dynamically build 1 to 100 list using loop pattern
        List<LearningItem> numberList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String numStr = String.valueOf(i);
            // Passing imageRes = 0 since numbers are rendered as text cards
            numberList.add(new LearningItem(numStr, 0, numStr));
        }

        setupResponsiveGrid(recyclerView, numberList);
    }

    private void setupResponsiveGrid(RecyclerView recyclerView, List<LearningItem> items) {
        float screenWidthDp = getResources().getDisplayMetrics().widthPixels /
                getResources().getDisplayMetrics().density;

        // Calculates column count dynamically for phones and tablets (~100dp for text cards)
        int spanCount = Math.max(3, (int) (screenWidthDp / 100));

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