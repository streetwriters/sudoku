package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.Controller.Stats;
import com.streetwriters.sudoku.Functions.FetchData.Data;
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
        gameState.setGameFinished(true);



        setButton(R.id.game_won_main_page, view -> {
            //gameState.resetSingleton();
            activity.startActivity(new Intent(activity, MainActivity.class));
        });

        setButton(R.id.game_won_rate_us, view->{
            openLink("market://details?id=" +  activity.getPackageName());
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
        new Data().deleteGameFile();
    }

    private void openLink(String link) {
        Uri uri = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            activity.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, " unable to find market app", Toast.LENGTH_LONG).show();
            Log.d("TEST1", "launchMarket: "+e.getMessage());
        }
    }
}
