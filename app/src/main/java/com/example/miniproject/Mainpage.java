package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mainpage extends AppCompatActivity {

    Button btn;
    Button btnLanguage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        // Start Learning button
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Mainpage.this, SelectionActivity.class);
                startActivity(i1);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

//        // Language button
//        btnLanguage = findViewById(R.id.btnLanguage);
//        btnLanguage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Mainpage.this, LanguageSelectionActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
