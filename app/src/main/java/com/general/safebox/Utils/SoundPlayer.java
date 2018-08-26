package com.general.safebox.Utils;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer {
    public static void playSound(Context context, int soundResource) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResource);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mediaPlayer1 -> mediaPlayer1.release());
    }
}
