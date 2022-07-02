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
        return Double.isNaN(gamesWon / gamesStarted) ? 0 : gamesWon / gamesStarted;
    }

    public double averageTime() {
        if (gamesWon != 0)
            return Double.isNaN(gamesWonTime / gamesWon) ? 0 : gamesWonTime / gamesWon;
        else
            return 0;
    }

    public double averageScore() {
        return Double.isNaN(totalScore / gamesWon) ? 0 : (totalScore / gamesWon);
    }

    public double weeklyWinRate() {
        int GamesStartedLastWeek = 0;
        int GamesWonLastWeek = 0;

        for (int index = (gameList.size() - 1); index > -1; index--) {
            GamesStartedLastWeek++;
            if (gameList.get(index).getResult() == 1) {
                GamesWonLastWeek++;
            }
            if (GamesStartedLastWeek == 7) {
                break;
            }
        }
        return Double.isNaN(GamesWonLastWeek / GamesStartedLastWeek) ? 0 : (GamesWonLastWeek / GamesStartedLastWeek);
    }

    public int gamesStarted() {
        return gamesStarted;
    }

    public void getWeekLimit(){
        int days = (int)TimeUnit.MILLISECONDS.toDays(gameList.get(gameList.size()-1).getStartDate());
        long lastWeekLimit = TimeUnit.DAYS.toMillis(days-7);;

        for (int index = (gameList.size() - 1); index > -1; index--) {

            if(gameList.get(index).getStartDate()>=lastWeekLimit){
                Log.d(GamesWon.class.getSimpleName(), "getWeekLimit: "+gameList.get(index).getStartDate());
            }else{
                break;
            }
        }
    }
}
