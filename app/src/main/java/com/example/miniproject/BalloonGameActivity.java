package com.example.miniproject;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class BalloonGameActivity extends AppCompatActivity {

    private FrameLayout balloonContainer;
    private TTSHelper ttsHelper;
    private final Handler spawnHandler = new Handler(Looper.getMainLooper());
    private final Random random = new Random();

    private final String[] symbols = {"A", "B", "C", "1", "2", "3", "Red", "Blue"};
    private final int[] colors = {
            Color.parseColor("#FF5722"), // Orange
            Color.parseColor("#E91E63"), // Pink
            Color.parseColor("#9C27B0"), // Purple
            Color.parseColor("#2196F3"), // Blue
            Color.parseColor("#4CAF50")  // Green
    };

    private final Runnable spawnRunnable = new Runnable() {
        @Override
        public void run() {
            spawnBalloon();
            spawnHandler.postDelayed(this, 1500); // Spawn new balloon every 1.5 seconds
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon_game);

        balloonContainer = findViewById(R.id.balloonContainer);
        ttsHelper = new TTSHelper(this);

        spawnHandler.post(spawnRunnable);
    }

    private void spawnBalloon() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        String randomSymbol = symbols[random.nextInt(symbols.length)];
        int randomColor = colors[random.nextInt(colors.length)];

        BalloonView balloon = new BalloonView(this, randomSymbol, randomColor);

        // Size of the balloon (e.g., 100dp x 100dp)
        int balloonSize = (int) (100 * getResources().getDisplayMetrics().density);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(balloonSize, balloonSize);

        // Random X position ensuring it stays fully inside the screen
        int maxX = Math.max(1, screenWidth - balloonSize);
        params.leftMargin = random.nextInt(maxX);
        balloon.setLayoutParams(params);

        // On Click/Tap handling
        balloon.setOnClickListener(v -> {
            ttsHelper.speak(balloon.getSymbol());
            balloon.popAnimation(() -> balloonContainer.removeView(balloon));
        });

        balloonContainer.addView(balloon);
        balloon.startRising(screenHeight, 4000); // 4 seconds to float to the top
    }

    @Override
    protected void onDestroy() {
        spawnHandler.removeCallbacks(spawnRunnable);
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}