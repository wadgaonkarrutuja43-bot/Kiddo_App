package com.example.miniproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FruitsActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private RecyclerView recyclerView;

    // Paired arrays for fruit images and names
    private final int[] fruitImages = {
            R.drawable.apple, R.drawable.mango, R.drawable.chikoo,
            R.drawable.pineapple, R.drawable.cherry, R.drawable.guava,
            R.drawable.banana, R.drawable.strawberry,
            R.drawable.watermelon, R.drawable.papaya, R.drawable.orange, R.drawable.grapes
    };

    private final String[] fruitNames = {
            "Apple", "Mango", "Chikoo",
            "Pineapple", "Cherry", "Guava",
            "Banana", "Strawberry",
            "Watermelon", "Papaya", "Orange", "Grapes"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        // Set Title
        TextView title = findViewById(R.id.titleText);
        if (title != null) {
            title.setText("FRUITS");
        }

        recyclerView = findViewById(R.id.recyclerView);

        // Build list of LearningItems using loop pattern
        List<LearningItem> fruitList = new ArrayList<>();
        for (int i = 0; i < fruitNames.length; i++) {
            String name = fruitNames[i];
            int imageRes = fruitImages[i];
            String speechText = "This is " + name.toLowerCase();

            fruitList.add(new LearningItem(name, imageRes, speechText));
        }

        setupResponsiveGrid(recyclerView, fruitList);
    }

    private void setupResponsiveGrid(RecyclerView recyclerView, List<LearningItem> items) {
        float screenWidthDp = getResources().getDisplayMetrics().widthPixels /
                getResources().getDisplayMetrics().density;

        // Dynamic column calculation (~140dp per item)
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