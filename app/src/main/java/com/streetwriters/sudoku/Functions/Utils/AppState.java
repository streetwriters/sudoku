package com.streetwriters.sudoku.Functions.Utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.preference.PreferenceManager;

import com.google.android.material.slider.Slider;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;

public class AppState {
    GameState gameState = GameState.getInstance();

    public int getTheme(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        int theme = sp.getInt("theme", 0);
        return theme;
    }

    public void setTheme(int theme){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("theme",theme);
        editor.apply();
    }

//    public boolean getSoundEffects(){
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
//        boolean sound = sp.getBoolean("music", true);
//        return sound;
//    }

    public void setSoundEffects(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putBoolean("music",music);
//        editor.apply();
        boolean sound = sp.getBoolean("sound", true);
        gameState.setSoundEffects(sound);
    }

}
