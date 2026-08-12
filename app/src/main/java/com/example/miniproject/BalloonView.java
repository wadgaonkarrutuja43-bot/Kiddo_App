package com.example.miniproject;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BalloonView extends FrameLayout {

    private TextView txtSymbol;
    private ObjectAnimator riseAnimator;

    public BalloonView(Context context, String symbol, int colorHex) {
        super(context);
        init(symbol, colorHex);
    }

    private void init(String symbol, int colorHex) {
        // Create circle shape dynamically using your color parsing logic
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(colorHex);

        this.setBackground(shape);

        // Add text label in the middle of the balloon
        txtSymbol = new TextView(getContext());
        txtSymbol.setText(symbol);
        txtSymbol.setTextSize(28f); // Fixed unit syntax
        txtSymbol.setTextColor(Color.WHITE);
        txtSymbol.setGravity(Gravity.CENTER);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(txtSymbol, params);
    }

    public void startRising(int screenHeight, long duration) {
        riseAnimator = ObjectAnimator.ofFloat(this, "translationY", screenHeight, -300f);
        riseAnimator.setDuration(duration);
        riseAnimator.setInterpolator(new LinearInterpolator());
        riseAnimator.start();
    }

    public void popAnimation(Runnable onComplete) {
        if (riseAnimator != null) riseAnimator.cancel();
        this.animate()
                .scaleX(1.4f)
                .scaleY(1.4f)
                .alpha(0f)
                .setDuration(150)
                .withEndAction(onComplete)
                .start();
    }

    public String getSymbol() {
        return txtSymbol.getText().toString();
    }
}