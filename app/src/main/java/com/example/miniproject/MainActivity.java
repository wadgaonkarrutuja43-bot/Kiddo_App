package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2, bt3, bt4, bt5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receiving mode from intent
        String mode = getIntent().getStringExtra("selected_mode");
        if (mode != null) {
            Toast.makeText(this, "Mode: " + mode, Toast.LENGTH_SHORT).show();
        }

        // Initializing buttons
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);

        // Button 1: Numbers Activity
        bt1.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, NumbersActivity.class);
            startActivity(i);
        });

        // Button 2: A to Z Activity
        bt2.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, A_To_Z.class);
            startActivity(i);
        });

        // Button 3: Color Activity
        bt3.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, color.class);
            startActivity(i);
        });

        // Button 4: Animals Activity
        bt4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnimalsActivity.class);
            startActivity(intent);
        });

        // Button 5: Fruits Activity
        bt5.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FruitsActivity.class);
            startActivity(intent);
        });
    }
}
