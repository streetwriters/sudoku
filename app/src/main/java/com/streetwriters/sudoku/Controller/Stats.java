package com.streetwriters.sudoku.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.Functions.Objects.DailyChallengeClass;
import com.streetwriters.sudoku.Functions.Objects.Game;

import java.util.ArrayList;

public class Stats {
    int noOfMistakes;
    Activity activity;
    int TOATL_SCORE = 88;
    Game game;
    int result;
    GameState gameState = GameState.getInstance();

    public Stats(int noOfMistakes, Activity activity, int result) {
        this.activity = activity;
        this.noOfMistakes = noOfMistakes;
        this.result = result;
        game = new Game();
        game.setDifficulty(getGameDifficulty());
        game.setGameNo(getGameNumber());
        game.setMistakes(noOfMistakes);
        game.setResult(result);
        game.setScore(getTotalScore());
        game.setTime(getTimeinMinutes());
        game.setStartTime(gameState.getStartTime());

        if (getGameDifficulty() == 4) {
            game.setDailyChallengeClass(getDailyChallengeClass());
        }

        saveStats();
    }

    public double getTotalScore() {
        double Score = TOATL_SCORE - (noOfMistakes * 2) - (getTimeinMinutes() * 0.1);
        return Score;
    }

    public int getTimeinMinutes() {
        return gameState.getGameTimer()/60;
    }

    public int getGameNumber() {// addition of games no should not be done here
        SharedPreferences settings = activity.getSharedPreferences("gamePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        int gameNo = settings.getInt("gameNo", 0) + 1;
        editor.putInt("gameNo", gameNo);
        editor.commit();
        return gameNo;
    }

    public int getGameDifficulty() { //should be done some place else
        return activity.getIntent().getIntExtra("difficulty", 0);
    }

    public DailyChallengeClass getDailyChallengeClass() {
        int day = activity.getIntent().getIntExtra("day", 0);
        int month = activity.getIntent().getIntExtra("month", 0);
        int year = activity.getIntent().getIntExtra("year", 0);

        DailyChallengeClass dailyChallengeClass = new DailyChallengeClass();
        dailyChallengeClass.setYear(year);
        dailyChallengeClass.setMonth(month);
        dailyChallengeClass.setDay(day);
        return dailyChallengeClass;
    }

    public void saveStats() { //saving of file should be done assets
        ArrayList<Game> gameList = new Data().getGameStats();
        gameList.add(game);
        new Data().setGameStats(gameList);

        for (int index = 0; index < gameList.size(); index++) {
            Log.d("test", "Difficulty:" + gameList.get(index).getDifficulty() + "\nGame NO:" + gameList.get(index).getGameNo() + "\nMistakes: " + gameList.get(index).getMistakes() + "\nGameResult:" + gameList.get(index).getResult()
                    + "\nScore: " + gameList.get(index).getScore() + "\nTime: " + gameList.get(index).getTime());
        }
    }
}
