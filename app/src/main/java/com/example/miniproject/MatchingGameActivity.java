package com.example.miniproject;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MatchingGameActivity extends AppCompatActivity {

    private TTSHelper ttsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_game);

        ttsHelper = new TTSHelper(this);

        ImageView sourceView = findViewById(R.id.imgSourceApple);
        ImageView targetView = findViewById(R.id.imgTargetAppleSlot);

        // Tags must match for a successful drop
        sourceView.setTag("apple");
        targetView.setTag("apple");

        setupDragAndDrop(sourceView, targetView);
    }

    private void setupDragAndDrop(View sourceView, View targetView) {
        // 1. Touch listener to initiate dragging
        sourceView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE); // Hide original while dragging
                return true;
            }
            return false;
        });

        // 2. Drag listener on the drop zone
        targetView.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;

                case DragEvent.ACTION_DROP:
                    View draggedView = (View) event.getLocalState();

                    // Null checks to prevent NullPointerException on missing tags
                    if (draggedView != null
                            && draggedView.getTag() != null
                            && v.getTag() != null
                            && draggedView.getTag().equals(v.getTag())) {

                        ttsHelper.speak("Great Job!");
                        animateBounce(v);
                        draggedView.setVisibility(View.INVISIBLE);
                    } else {
                        ttsHelper.speak("Try Again!");
                        if (draggedView != null) {
                            draggedView.setVisibility(View.VISIBLE); // Show again if drop failed
                        }
                    }
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    View view = (View) event.getLocalState();
                    // If drag was cancelled or missed, restore visibility
                    if (!event.getResult() && view != null) {
                        view.setVisibility(View.VISIBLE);
                    }
                    return true;
            }
            return true;
        });
    }

    private void animateBounce(View view) {
        view.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(150)
                .withEndAction(() -> view.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .setDuration(150)
                        .start())
                .start();
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}