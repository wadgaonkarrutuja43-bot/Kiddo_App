package com.example.miniproject;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;

public class ColoringGameActivity extends AppCompatActivity {

    private ColoringCanvasView canvasView;
    private TTSHelper ttsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coloring_canvas);

        ttsHelper = new TTSHelper(this);
        canvasView = findViewById(R.id.canvasView);

        // Color Listeners
        findViewById(R.id.btnRed).setOnClickListener(v -> setCanvasColor(Color.RED, "Red"));
        findViewById(R.id.btnBlue).setOnClickListener(v -> setCanvasColor(Color.BLUE, "Blue"));
        findViewById(R.id.btnGreen).setOnClickListener(v -> setCanvasColor(Color.GREEN, "Green"));
        findViewById(R.id.btnYellow).setOnClickListener(v -> setCanvasColor(Color.YELLOW, "Yellow"));
        findViewById(R.id.btnPurple).setOnClickListener(v -> setCanvasColor(Color.parseColor("#D500F9"), "Purple"));
        findViewById(R.id.btnOrange).setOnClickListener(v -> setCanvasColor(Color.parseColor("#FF9100"), "Orange"));

        // Brush Sizes
        findViewById(R.id.btnSizeSmall).setOnClickListener(v -> {
            canvasView.setStrokeWidth(12f);
            ttsHelper.speak("Small brush");
        });
        findViewById(R.id.btnSizeMedium).setOnClickListener(v -> {
            canvasView.setStrokeWidth(24f);
            ttsHelper.speak("Medium brush");
        });
        findViewById(R.id.btnSizeLarge).setOnClickListener(v -> {
            canvasView.setStrokeWidth(40f);
            ttsHelper.speak("Large brush");
        });

        // Tool Actions
        findViewById(R.id.btnEraser).setOnClickListener(v -> {
            canvasView.setEraserMode();
            ttsHelper.speak("Eraser");
        });

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            canvasView.clearCanvas();
            ttsHelper.speak("Clear page");
        });

        findViewById(R.id.btnSave).setOnClickListener(v -> saveArtworkToGallery());
    }

    private void setCanvasColor(int color, String colorName) {
        canvasView.setBrushColor(color);
        ttsHelper.speak(colorName);
    }

    public Bitmap getBitmap() {
        if (canvasView == null || canvasView.getWidth() <= 0 || canvasView.getHeight() <= 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(canvasView.getWidth(), canvasView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        canvasView.draw(canvas);
        return bitmap;
    }

    private void saveArtworkToGallery() {
        Bitmap bitmap = getBitmap();
        if (bitmap == null) {
            Toast.makeText(this, "Canvas is not ready yet", Toast.LENGTH_SHORT).show();
            return;
        }

        String filename = "Artwork_" + System.currentTimeMillis() + ".png";

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MiniProjectArtwork");

                Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (imageUri != null) {
                    OutputStream fos = getContentResolver().openOutputStream(imageUri);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    if (fos != null) fos.close();
                }
            } else {
                MediaStore.Images.Media.insertImage(
                        getContentResolver(),
                        bitmap,
                        filename,
                        "Kids Educational App Artwork"
                );
            }

            ttsHelper.speak("Great job! Artwork saved!");
            Toast.makeText(this, "Artwork saved to Gallery!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save artwork", Toast.LENGTH_SHORT).show();
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