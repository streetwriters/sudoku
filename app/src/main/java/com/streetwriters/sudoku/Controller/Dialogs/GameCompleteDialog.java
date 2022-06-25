package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.Controller.Stats;
import com.streetwriters.sudoku.Functions.Utils.Congratulations;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;

public class GameCompleteDialog extends DialogHelper {
    Activity activity;
    GameState gameState = GameState.getInstance();

    public GameCompleteDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void Show() {
        setLayout(R.layout.game_won);

        setButton(R.id.main_page, view -> {
            gameState.resetSingleton();
            activity.startActivity(new Intent(activity, MainActivity.class));
        });

        setCustomView();
        setCancel(false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDialog().show();
            }
        }, 10000);

        Congratulations congratulations = new Congratulations();
        congratulations.startFireworks(activity, getCustomView());
        Stats scoreBoard = new Stats(gameState.getMistakes(), activity, 1);
    }
}
