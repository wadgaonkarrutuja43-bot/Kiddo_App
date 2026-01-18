package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Button btnLearning = findViewById(R.id.btnLearning);
        Button btnGames = findViewById(R.id.btnGames);

        btnLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, MainActivity.class);
                intent.putExtra("selected_mode", "learning");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        btnGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Number Sequencing Game
                startActivity(new Intent(SelectionActivity.this, ChooseGameActivity2.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
