package com.example.miniproject;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.ViewHolder> {

    private final List<LearningItem> itemList;
    private final TTSHelper ttsHelper;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public LearningAdapter(List<LearningItem> itemList, TTSHelper ttsHelper) {
        this.itemList = itemList;
        this.ttsHelper = ttsHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_learning_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningItem item = itemList.get(position);
        int currentPos = holder.getAdapterPosition();

        holder.txtTitle.setText(item.getTitle());

        String ttsText = item.getTtsText();

        // 1. Image & Dynamic Tinting Logic
        if (item.getImageResId() != 0) {
            holder.imgIcon.setVisibility(View.VISIBLE);
            holder.imgIcon.setImageResource(item.getImageResId());
        } else if (ttsText != null && ttsText.contains("#")) {
            holder.imgIcon.setVisibility(View.VISIBLE);
            String hexColor = ttsText.substring(ttsText.indexOf("#"));

            Drawable circleDrawable = AppCompatResources.getDrawable(holder.itemView.getContext(), R.drawable.circle_shape);
            if (circleDrawable != null) {
                Drawable wrappedDrawable = DrawableCompat.wrap(circleDrawable.mutate());
                DrawableCompat.setTint(wrappedDrawable, Color.parseColor(hexColor));
                holder.imgIcon.setImageDrawable(wrappedDrawable);
            }
        } else {
            holder.imgIcon.setVisibility(View.GONE);
        }

        // 2. Selection Highlight State
        boolean isSelected = (currentPos == selectedPosition);
        if (holder.cardView != null) {
            if (isSelected) {
                holder.cardView.setForeground(AppCompatResources.getDrawable(holder.itemView.getContext(), R.drawable.card_highlight_border));
                startPulsingAnimation(holder.cardView);
            } else {
                holder.cardView.setForeground(null);
                clearPulsingAnimation(holder.cardView);
            }
        }

        // 3. Click Listener: Tactile Animation + TTS Audio
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            animateBounce(v);

            int previousPosition = selectedPosition;
            selectedPosition = pos;
            if (previousPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousPosition);
            }
            notifyItemChanged(selectedPosition);

            if (ttsHelper != null) {
                if (ttsText != null && ttsText.contains("#")) {
                    ttsHelper.speak(item.getTitle());
                } else if (ttsText != null) {
                    ttsHelper.speak(ttsText);
                } else {
                    ttsHelper.speak(item.getTitle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // --- Animation Helpers ---

    private void animateBounce(View view) {
        view.setScaleX(0.92f);
        view.setScaleY(0.92f);
        view.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(150)
                .start();
    }

    private void startPulsingAnimation(View view) {
        ObjectAnimator pulseAnimator = (ObjectAnimator) view.getTag(R.id.pulse_animator);
        if (pulseAnimator == null) {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.04f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.04f);

            pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY);
            pulseAnimator.setDuration(600);
            pulseAnimator.setRepeatCount(ValueAnimator.INFINITE);
            pulseAnimator.setRepeatMode(ValueAnimator.REVERSE);

            view.setTag(R.id.pulse_animator, pulseAnimator);
        }
        if (!pulseAnimator.isRunning()) {
            pulseAnimator.start();
        }
    }

    private void clearPulsingAnimation(View view) {
        ObjectAnimator pulseAnimator = (ObjectAnimator) view.getTag(R.id.pulse_animator);
        if (pulseAnimator != null) {
            pulseAnimator.cancel();
            view.setTag(R.id.pulse_animator, null);
        }
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    // --- ViewHolder ---

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgIcon;
        TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // 1. Set cardView from root itemView
            if (itemView instanceof CardView) {
                cardView = (CardView) itemView;
            }

            // 2. Map views using your XML layout IDs (imgItem and txtItemTitle)
            imgIcon = itemView.findViewById(R.id.imgItem);
            txtTitle = itemView.findViewById(R.id.txtItemTitle);
        }
    }
}