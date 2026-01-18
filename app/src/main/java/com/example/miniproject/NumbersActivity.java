package com.example.miniproject;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Locale;

public class NumbersActivity extends Activity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

            //Get GridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setRowCount(25);
        gridLayout.setColumnCount(4);


        // Calculate screen width to set button size
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int buttonSize = screenWidth / 2; // Two buttons per row
        int bt=buttonSize/2;
        // Add buttons for numbers from 1 to 10
        for (int i = 1; i <= 100; i++) {
            Button button = new Button(this);
            button.setText(String.valueOf(i));
           button.setOnClickListener(this);
         GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = bt-50;
            params.height = bt;
          //params.setGravity(Gravity.FILL);
          params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1);  //span across 1 column
            button.setLayoutParams(params);
            button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
          gridLayout.addView(button);
       }
    }

//    public void speak(View v){
//        String text = v.getContentDescription().toString();
//        speakOut(text);
//
//    }
    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            String text = ((Button) v).getText().toString();
            speakOut(text);
        }
    }

    private void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech when activity is destroyed
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
