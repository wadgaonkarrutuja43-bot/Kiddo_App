package com.example.miniproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class color extends Activity implements View.OnClickListener {
    private final String[] colorNames = {"BLUE", "RED", "YELLOW", "ORANGE",
            "GREEN", "PURPLE", "PINK", "BLACK", "WHITE"};
    private final int[] colorValues = {Color.BLUE, Color.RED, Color.YELLOW,
            Color.parseColor("#FFA500"), // ORANGE
            Color.GREEN, Color.parseColor("#800080"), // PURPLE
            Color.parseColor("#FFC0CB"), // PINK
            Color.BLACK, Color.WHITE};

    private TTSHelper ttsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        // Initialize TTS
        ttsHelper = new TTSHelper(this);

        // Set title
        TextView title = findViewById(R.id.titleText);
        title.setText("COLOURS");

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(2); // 2 colors per row

        // Calculate button size based on screen width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int buttonSize = (screenWidth - 60) / 2; // Account for margins (20+20+20)

        for (int i = 0; i < colorNames.length; i++) {
            Button button = createRoundColorButton(colorNames[i], colorValues[i], buttonSize);
            gridLayout.addView(button);
        }
    }

    private Button createRoundColorButton(String colorName, int colorValue, int size) {
        Button button = new Button(this);
        button.setText(colorName);
        button.setBackground(getResources().getDrawable(R.drawable.round_color_button));
        button.getBackground().setTint(colorValue);
        button.setOnClickListener(this);

        // Set text color based on background brightness
        button.setTextColor(isDarkColor(colorValue) ? Color.WHITE : Color.BLACK);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = size;
        params.height = size;
        params.setMargins(20, 20, 20, 20);

        button.setLayoutParams(params);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        button.setAllCaps(true);

        return button;
    }

    private boolean isDarkColor(int color) {
        double darkness = 1 - (0.299 * Color.red(color) +
                0.587 * Color.green(color) +
                0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            String colorName = ((Button) v).getText().toString();
            ttsHelper.speakSlowly(colorName);
        }
    }

    @Override
    protected void onDestroy() {
        if (ttsHelper != null) {
            ttsHelper.shutdown();
        }
        super.onDestroy();
    }
}