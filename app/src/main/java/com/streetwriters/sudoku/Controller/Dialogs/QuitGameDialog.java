package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.content.Intent;

import com.streetwriters.sudoku.Controller.Play;
import com.streetwriters.sudoku.Functions.Utils.Singletons.LoadGameState;
import com.streetwriters.sudoku.Functions.SudukoTimer;
import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

public class QuitGameDialog extends DialogHelper {
    Activity activity;

    public QuitGameDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void Show(Play pauseAndPlay, SudukoTimer sudukoTimer) {
        setQuitMessage();
        setQuitTitle();
        setCancel(false);
        setQuitPositiveButton((dialog, which) -> {
            LoadGameState loadGameState = new LoadGameState(activity);
            loadGameState.saveGame();
            GameState.getInstance().resetSingleton();
            activity.startActivity(new Intent(activity, MainActivity.class));
        });

        setQuitNegativeButton((dialog, which) -> {
            pauseAndPlay.onResume();
            sudukoTimer.startTimer(activity);
        });

        getDialog().show();
    }
}
