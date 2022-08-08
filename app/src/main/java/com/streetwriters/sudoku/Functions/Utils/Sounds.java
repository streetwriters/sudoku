package com.streetwriters.sudoku.Functions.Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import androidx.annotation.RawRes;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

public class Sounds {
     public void playSound(Context context){
         if(GameState.getInstance().isSoundEffects()) {
//             MediaPlayer mp = MediaPlayer.create(context, id);
//             mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                 @Override
//                 public void onCompletion(MediaPlayer mp) {
//                     // TODO Auto-generated method stub
//                     //mp.reset();
//                     mp.release();
//                     //mp=null;
//                 }
//             });
//             final float maxVolume = 100.0f;
//             float volume = 25.0f;
//             float result = volume / maxVolume;
//             mp.setVolume(result, result);
//             mp.start();
             AudioManager audioManager =
                     (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
             audioManager.playSoundEffect(AudioManager.FX_KEY_CLICK);

         }
    }

    public void playSound(@RawRes int id, Context context,float volume){
        if(GameState.getInstance().isSoundEffects()) {
            MediaPlayer mp = MediaPlayer.create(context, id);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    //mp.reset();
                    mp.release();
                    //mp=null;
                }
            });

            final float maxVolume = 100.0f;
            //float currentVolume = 5.0f;
            float result = volume / maxVolume;
            mp.setVolume(result, result);
            mp.start();
        }
    }
}
