package com.example.miniproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ColoringCanvasView extends View {

    private Paint currentPaint;
    private Path currentPath;
    private final List<ColoredPath> paths = new ArrayList<>();

    private int currentColor = Color.RED;
    private float currentStrokeWidth = 24f;
    private boolean isEraserMode = false;

    private static class ColoredPath {
        Path path;
        int color;
        float strokeWidth;

        ColoredPath(Path path, int color, float strokeWidth) {
            this.path = path;
            this.color = color;
            this.strokeWidth = strokeWidth;
        }
    }

    public ColoringCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentPaint = new Paint();
        currentPaint.setAntiAlias(true);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPath = new Path();
    }

    public void setBrushColor(int color) {
        this.isEraserMode = false;
        this.currentColor = color;
    }

    public void setEraserMode() {
        this.isEraserMode = true;
    }

    public void setStrokeWidth(float sizeInDp) {
        float density = getResources().getDisplayMetrics().density;
        this.currentStrokeWidth = sizeInDp * density;
    }

    public void clearCanvas() {
        paths.clear();
        currentPath.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (ColoredPath cp : paths) {
            currentPaint.setColor(cp.color);
            currentPaint.setStrokeWidth(cp.strokeWidth);
            canvas.drawPath(cp.path, currentPaint);
        }

        currentPaint.setColor(isEraserMode ? Color.WHITE : currentColor);
        currentPaint.setStrokeWidth(currentStrokeWidth);
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                int activeColor = isEraserMode ? Color.WHITE : currentColor;
                paths.add(new ColoredPath(currentPath, activeColor, currentStrokeWidth));
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}

