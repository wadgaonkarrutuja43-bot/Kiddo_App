package com.example.miniproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class A_To_Z extends AppCompatActivity {

    private TTSHelper ttsHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ato_z);

        ttsHelper = new TTSHelper(this);
        recyclerView = findViewById(R.id.recyclerView);

        List<LearningItem> alphabetList = new ArrayList<>();

        // Map letters with their respective drawable icons and phonetics
        alphabetList.add(new LearningItem("A", R.drawable.apple, "A for Apple!"));
        alphabetList.add(new LearningItem("B", R.drawable.ball, "B for Ball!"));
        alphabetList.add(new LearningItem("C", R.drawable.cat, "C for Cat!"));
        alphabetList.add(new LearningItem("D", R.drawable.dog, "D for Dog!"));
        alphabetList.add(new LearningItem("E", R.drawable.elephant, "E for Elephant!"));
        alphabetList.add(new LearningItem("F", R.drawable.fish, "F for Fish!"));
        alphabetList.add(new LearningItem("G", R.drawable.giraffe, "G for Giraffe!"));
        alphabetList.add(new LearningItem("H", R.drawable.horse, "H for Horse!"));
        alphabetList.add(new LearningItem("I", R.drawable.icecream, "I for Ice Cream!"));
        alphabetList.add(new LearningItem("J", R.drawable.juice, "J for Juice!"));
        alphabetList.add(new LearningItem("K", R.drawable.kite, "K for Kite!"));
        alphabetList.add(new LearningItem("L", R.drawable.lion, "L for Lion!"));
        alphabetList.add(new LearningItem("M", R.drawable.monkey, "M for Monkey!"));
        alphabetList.add(new LearningItem("N", R.drawable.nest, "N for Nest!"));
        alphabetList.add(new LearningItem("O", R.drawable.owl, "O for Owl!"));
        alphabetList.add(new LearningItem("P", R.drawable.pineapple, "P for Penguin!"));
        alphabetList.add(new LearningItem("Q", R.drawable.queen, "Q for Queen!"));
        alphabetList.add(new LearningItem("R", R.drawable.rabbit, "R for Rabbit!"));
        alphabetList.add(new LearningItem("S", R.drawable.sun, "S for Sun!"));
        alphabetList.add(new LearningItem("T", R.drawable.tiger, "T for Tiger!"));
        alphabetList.add(new LearningItem("U", R.drawable.umbrella, "U for Umbrella!"));
        alphabetList.add(new LearningItem("V", R.drawable.van, "V for Van!"));
        alphabetList.add(new LearningItem("W", R.drawable.watermelon, "W for Watermelon!"));
        alphabetList.add(new LearningItem("X", R.drawable.xylophone, "X for Xylophone!"));
        alphabetList.add(new LearningItem("Y", R.drawable.yak, "Y for Yak!"));
        alphabetList.add(new LearningItem("Z", R.drawable.zebra, "Z for Zebra!"));

        setupResponsiveGrid(recyclerView, alphabetList);
    }

    private void setupResponsiveGrid(RecyclerView recyclerView, List<LearningItem> items) {
        float screenWidthDp = getResources().getDisplayMetrics().widthPixels /
                getResources().getDisplayMetrics().density;

        // Dynamic 2-3 column layout
        int spanCount = Math.max(2, (int) (screenWidthDp / 150));

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