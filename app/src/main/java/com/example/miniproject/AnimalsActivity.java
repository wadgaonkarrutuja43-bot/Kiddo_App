package com.example.miniproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AnimalsActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        recyclerView = findViewById(R.id.recyclerView);

        // Prepare Data using your original animals and custom TTS sentences
        List<LearningItem> animalList = new ArrayList<>();
        animalList.add(new LearningItem("Lion", R.drawable.lion, "L is for Lion! Roar!"));
        animalList.add(new LearningItem("Elephant", R.drawable.elephant, "E is for Elephant!"));
        animalList.add(new LearningItem("Tiger", R.drawable.tiger, "T is for Tiger!"));
        animalList.add(new LearningItem("Monkey", R.drawable.monkey, "M is for Monkey!"));
        animalList.add(new LearningItem("Cow", R.drawable.cow, "C is for Cow! Moo!"));
        animalList.add(new LearningItem("Dog", R.drawable.dog, "D is for Dog! Woof woof!"));
        animalList.add(new LearningItem("Cat", R.drawable.cat, "C is for Cat! Meow!"));
        animalList.add(new LearningItem("Sheep", R.drawable.sheep, "S is for Sheep! Baa!"));
        animalList.add(new LearningItem("Horse", R.drawable.horse, "H is for Horse!"));
        animalList.add(new LearningItem("Pig", R.drawable.pig, "P is for Pig! Oink oink!"));

        // Bind Responsive Grid (Handles LayoutManager & Adapter)
        setupResponsiveGrid(recyclerView, animalList);
    }

    private void setupResponsiveGrid(RecyclerView recyclerView, List<LearningItem> items) {
        // Determine screen width in DP
        float screenWidthDp = getResources().getDisplayMetrics().widthPixels /
                getResources().getDisplayMetrics().density;

        // Allocate ~150dp per grid item dynamically (min 2 columns)
        int spanCount = Math.max(2, (int) (screenWidthDp / 150));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        LearningAdapter adapter = new LearningAdapter(items, ttsHelper);
        recyclerView.setAdapter(adapter);
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