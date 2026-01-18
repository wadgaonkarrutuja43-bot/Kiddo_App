// TTSHelper.java
package com.example.miniproject;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import java.util.Locale;

public class TTSHelper implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private boolean isLoaded = false;
    private Context context;

    public TTSHelper(Context context) {
        this.context = context;
        tts = new TextToSpeech(context, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported");
            } else {
                isLoaded = true;
                // Set slower speech rate (0.8f is 80% of normal speed)
                tts.setSpeechRate(0.7f);
                // Higher pitch for kid-friendly voice
                tts.setPitch(1.2f);
            }
        } else {
            Log.e("TTS", "Initialization failed");
        }
    }

    public void speakSlowly(String text) {
        if (isLoaded) {
            // Clear any previous speech
            tts.stop();
            // Speak with slower rate
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "slow_tts");
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}