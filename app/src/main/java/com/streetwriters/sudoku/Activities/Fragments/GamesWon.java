package com.streetwriters.sudoku.Activities.Fragments;

import android.util.Log;

import com.streetwriters.sudoku.Functions.Objects.Game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GamesWon {
    private ArrayList<Game> gameList;
    private int gamesWon = 0;
    private int gamesWonTime = 0;
    private double totalScore = 0;
    private double bestScore = 0;
    private double bestTime = 0;
    private int gamesStarted = 0;

    public GamesWon(ArrayList<Game> gameList) {
        this.gameList = gameList;
        setGamesCompleted();
    }

    private void setGamesCompleted() {
        for (int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);
            gamesStarted++;
            if (game.getResult() == 1) {
                gamesWon++;
                gamesWonTime = game.getTime() + gamesWonTime;
                totalScore = totalScore + game.getScore();

                if (bestScore < game.getScore()) {
                    bestScore = game.getScore();
                }

                if (bestTime > game.getTime()) {
                    bestTime = game.getTime();
                }
            }
        }
    }

    public double bestScore() {
        return bestScore;
    }

    public int gamesWon() {
        return gamesWon;
    }

    public double bestTime() {
        return bestTime;
    }

    public double winRate() {
        if (gamesStarted != 0)
            return gamesWon / gamesStarted;
        else
            return 0;
    }

    public double averageTime() {
        if (gamesWon != 0)
            return gamesWonTime / gamesWon;
        else
            return 0;
    }

    public double averageScore() {
        if (gamesWon != 0)
            return (totalScore / gamesWon);
        else
            return 0;
    }

    public double weeklyWinRate() {
        int gamesStartedLastWeek = 0;
        int gamesWonLastWeek = 0;

        for (int index = (gameList.size() - 1); index > -1; index--) {
            gamesStartedLastWeek++;
            if (gameList.get(index).getResult() == 1) {
                gamesWonLastWeek++;
            }
            if (gameList.get(index).getStartDate() < getWeekLimit()) {
                break;
            }
        }
        if (gamesStartedLastWeek != 0)
            return (gamesWonLastWeek / gamesStartedLastWeek);
        else
            return 0;
    }

    public int gamesStarted() {
        return gamesStarted;
    }

    public long getWeekLimit() {
        int days = (int) TimeUnit.MILLISECONDS.toDays(gameList.get(gameList.size() - 1).getStartDate());
        long weekLimit = TimeUnit.DAYS.toMillis(days - 7);

        for (int index = (gameList.size() - 1); index > -1; index--) {
            if (gameList.get(index).getStartDate() >= weekLimit) {
                Log.d(GamesWon.class.getSimpleName(), "getWeekLimit: " + gameList.get(index).getStartDate());
            } else {
                break;
            }
        }

        return weekLimit;
    }
}
