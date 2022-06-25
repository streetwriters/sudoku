package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.content.Intent;

import com.streetwriters.sudoku.Activities.GameActivity;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;

public class DifficultyDialog extends DialogHelper {
    GameState gameState = GameState.getInstance();

    public DifficultyDialog(Activity activity) {
        super(activity);
    }

    public void Show() {
        setLayout(R.layout.difficult_options);

        setButton(R.id.easy_dialog, v -> {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 1);
            gameState.setAlertDialogPresent(false);
            activity.onBackPressed();
            reset();
            activity.startActivity(intent);
            getDialog().dismiss();
        });

        setButton(R.id.medium_dialog, v -> {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 2);
            gameState.setAlertDialogPresent(false);
            activity.onBackPressed();
            reset();
            activity.startActivity(intent);
            getDialog().dismiss();
        });

        setButton(R.id.hard_dialog, v -> {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 3);
            gameState.setAlertDialogPresent(false);
            activity.onBackPressed();
            reset();
            activity.startActivity(intent);
            getDialog().dismiss();
        });

        setCustomView();
        setCancel(false);
        getDialog().show();
    }

    private void reset() {
        gameState.resetSingleton();
    }
}
