package com.example.miniproject;

public class LearningItem {
    private String title;
    private int imageResId; // e.g., R.drawable.ic_apple
    private String ttsText;  // What TTS will speak, e.g., "Apple! A for Apple."

    public LearningItem(String title, int imageResId, String ttsText) {
        this.title = title;
        this.imageResId = imageResId;
        this.ttsText = ttsText;
    }

    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
    public String getTtsText() { return ttsText; }
}