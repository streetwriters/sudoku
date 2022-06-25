package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.Controller.Stats;
import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;


public class GameOverDialog extends DialogHelper {
    GameState gameState = GameState.getInstance();

    public GameOverDialog(Activity activity) {
        super(activity);
    }

    public void Show() {
        setLayout(R.layout.dialog_layout);
        setCustomView();
        setCancel(false);

        setButton(R.id.second_chance, v -> {
            secondChance();
            getDialog().dismiss();
        });

        setButton(R.id.new_game, v -> {
            Stats scoreBoard = new Stats(gameState.getMistakes(), activity, 0);
            //LoadData data= new LoadData(activity);
            //data.DeleteSavedPuzzleData();
            new Data().deleteGameFile();
            new DifficultyDialog(activity).Show();
            getDialog().dismiss();

        });

        getDialog().show();
    }

    void secondChance() {
        if (gameState.getmRewardedVideoAd().isLoaded()) {
            gameState.setGameOverReward(true);
            gameState.getmRewardedVideoAd().show();
        } else {
            Stats scoreBoard = new Stats(gameState.getMistakes(), activity, 0);
            //LoadData data= new LoadData(activity);
            //data.DeleteSavedPuzzleData();
            new Data().deleteGameFile();
            Toast.makeText(activity, "No Ads to Show", Toast.LENGTH_LONG).show();
            activity.startActivity(new Intent(activity, MainActivity.class));
        }
    }

}
