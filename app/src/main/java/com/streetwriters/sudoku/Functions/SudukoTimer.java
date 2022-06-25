package com.streetwriters.sudoku.Functions;

import android.app.Activity;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Layouts.TextLayout;

import java.util.Timer;
import java.util.TimerTask;

public class SudukoTimer {
    public Timer T;
    public Boolean timer = false;
    GameState gameState = GameState.getInstance();

    public SudukoTimer() {

    }

    public void startTimer(Activity activity) {
        T = new Timer();

        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                activity.runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        int count= gameState.getGameTimer();
                        new TextLayout(activity).setTimer(count);

                        gameState.setGameTimer(gameState.getGameTimer()+1);

                    }
                });
            }
        }, 1000, 1000);

        timer = true;
    }
}
