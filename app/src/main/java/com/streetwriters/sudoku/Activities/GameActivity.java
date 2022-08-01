package com.streetwriters.sudoku.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.streetwriters.sudoku.Functions.Utils.Singletons.LoadGameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Controller.Dialogs.QuitGameDialog;
import com.streetwriters.sudoku.Controller.Play;
import com.streetwriters.sudoku.Controller.GameController;
import com.streetwriters.sudoku.Functions.SudukoTimer;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

import com.streetwriters.sudoku.View.Ui.RewardedAd;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;


/*
 * Classes to be changed;
 * Game//
 * CheckErrors
 * Remove Class style
 * Clean intentData, puzzle data etc classes
 * Clean stats class
 * */

public class GameActivity extends AppCompatActivity {

    private GameController gameController;
    private SudukoTimer sudukoTimer;
    private Play pauseAndPlay;
    private GameState gameState = GameState.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pauseAndPlay = new Play(this);
        sudukoTimer = new SudukoTimer();
        sudukoTimer.startTimer(this);

        gameController = new GameController(this);

          RewardedAd rewardedAd = new RewardedAd(this);
          gameState.setRewardedAd(rewardedAd);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option_play_pause) {
            if (sudukoTimer.timer) {
                sudukoTimer.T.cancel();
                sudukoTimer.timer = false;
                pauseAndPlay.onPause();
                item.setIcon(R.drawable.ic_play);
            } else {
                sudukoTimer.startTimer(this);
                pauseAndPlay.onResume();
                item.setIcon(R.drawable.ic_pause);
            }
        } else if(id==R.id.menu_help){
           startActivity(new Intent(this,Help.class));
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));

        sudukoTimer.T.cancel();
        //pauseAndPlay.onPause();

//        if (gameState.getAlertDialogPresent()) {
//            QuitGameDialog quit = new QuitGameDialog(this);
//            quit.Show(pauseAndPlay, sudukoTimer);
//        } else {
//            gameState.setAlertDialogPresent(true);
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        sudukoTimer.T.cancel();
        return true;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(GameActivity.class.getSimpleName(), "onSaveInstanceState: " +
                "isGameFinished: "+!gameState.isGameFinished()+
                "mistakes: "+ (gameState.getMistakes()<3));
        if (!gameState.isGameFinished() && gameState.getMistakes() < 3) {
            new LoadGameState(this).saveGame();
        }
    }
}
