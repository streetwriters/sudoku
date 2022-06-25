package com.streetwriters.sudoku.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Controller.Dialogs.QuitGameDialog;
import com.streetwriters.sudoku.Controller.Game;
import com.streetwriters.sudoku.Controller.GameController;
import com.streetwriters.sudoku.Functions.SudukoTimer;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

/*
 * Classes to be changed;
 * Game//
 * CheckErrors
 * Remove Class style
 * Clean intentData, puzzle data etc classes
 * Clean stats class
 * */

public class GameActivity extends AppCompatActivity {

    GameController gameController;
    SudukoTimer sudukoTimer;
    Game pauseAndPlay;
    GameState gameState = GameState.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_settings);
        toolbar.setOverflowIcon(drawable);


        pauseAndPlay = new Game(this);
        sudukoTimer = new SudukoTimer();
        sudukoTimer.startTimer(this);

//        InitializeAds initializeAds= new InitializeAds(this);
//        initializeAds.initializeAdView(this);
//        initializeAds.initializeMobileAds(this);
//        initializeAds.initializeMobileAdswithId(this);
//        initializeAds.initializeRewardedVideoAd(this);
//        initializeAds.loadRewardedVideoAd();

        gameController = new GameController(this);
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
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        sudukoTimer.T.cancel();
        pauseAndPlay.onPause();

        if (gameState.getAlertDialogPresent()) {
            QuitGameDialog quit = new QuitGameDialog(this);
            quit.Show(pauseAndPlay, sudukoTimer);
        } else {
            gameState.setAlertDialogPresent(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
