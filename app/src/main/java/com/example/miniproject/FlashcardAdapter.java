package com.example.miniproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {

    private final List<LearningItem> itemList;
    private final TTSHelper ttsHelper;

    public FlashcardAdapter(List<LearningItem> itemList, TTSHelper ttsHelper) {
        this.itemList = itemList;
        this.ttsHelper = ttsHelper;
    }

    @NonNull
    @Override
    public FlashcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flashcard, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardViewHolder holder, int position) {
        LearningItem item = itemList.get(position);

        holder.imgFlashcard.setImageResource(item.getImageResId());
        holder.txtTitle.setText(item.getTitle());

        // Tap animation & TTS speech feedback
        holder.itemView.setOnClickListener(v -> {
            v.animate()
                    .scaleX(1.05f)
                    .scaleY(1.05f)
                    .setDuration(120)
                    .withEndAction(() -> v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120).start())
                    .start();

            ttsHelper.speak(item.getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFlashcard;
        TextView txtTitle;

        public FlashcardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFlashcard = itemView.findViewById(R.id.imgFlashcard);
            txtTitle = itemView.findViewById(R.id.txtFlashcardTitle);
        }
    }
}