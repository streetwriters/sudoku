package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.app.Dialog;

import com.streetwriters.sudoku.Controller.Stats;
import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;


public class GameOverDialog extends DialogHelper {
    GameState gameState = GameState.getInstance();
    Dialog dialog;

    public GameOverDialog(Activity activity) {
        super(activity);
    }

    public void Show() {
        setLayout(R.layout.dialog_layout);
        setCustomView();
        setCancel(false);
        this.dialog = getDialog();
        Stats scoreBoard = new Stats(gameState.getMistakes(), activity, 0);
        new Data().deleteGameFile();
        gameState.setGameFinished(true);
        setButton(R.id.second_chance, v -> {
            secondChance();
            //dialog.dismiss();
        });

        setButton(R.id.new_game, v -> {
            dialog.dismiss();
            new DifficultyDialog(activity).Show();
        });

        dialog.show();
    }

    void secondChance() {
        gameState.setAdTypeHint(false);
        gameState.getRewardedAd().displayRewardedAd(dialog);
    }

}
