package com.example.miniproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        ttsHelper = new TTSHelper(this);

        ViewPager2 viewPager = findViewById(R.id.viewPagerFlashcards);

        // Load sample data using your LearningItem model
        List<LearningItem> flashcardData = loadFlashcards();

        FlashcardAdapter adapter = new FlashcardAdapter(flashcardData, ttsHelper);
        viewPager.setAdapter(adapter);

        // Auto-speak when a child swipes to a new card
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LearningItem currentItem = flashcardData.get(position);
                ttsHelper.speak(currentItem.getTitle());
            }
        });
    }

    private List<LearningItem> loadFlashcards() {
        List<LearningItem> list = new ArrayList<>();

        // Pass 3 arguments: Title, Image Resource, Category/Description
        list.add(new LearningItem("Apple", R.drawable.apple, "Fruit"));
        list.add(new LearningItem("Banana", R.drawable.banana, "Fruit"));
        list.add(new LearningItem("Cat", R.drawable.cat, "Animal"));
        list.add(new LearningItem("Dog", R.drawable.dog, "Animal"));

        return list;
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}