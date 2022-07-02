package com.streetwriters.sudoku.Activities.Fragments;

import com.streetwriters.sudoku.Functions.Objects.Game;

import java.util.ArrayList;

public class Streak {
    ArrayList<Game> gameList;
    int[] streaks = new int[3000];
    int streakIndex = 0;
    int noMistake = 0;

    public Streak(ArrayList<Game> gameList){
        this.gameList = gameList;
        setStreak();
    }

    private void setStreak() {
        int streakSize = 0;

        for (int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);

            if (game.getResult() == 1) {
                if (game.getMistakes() == 0) { //adding a number to a streak
                    noMistake++;
                    streakSize++;
                    streaks[streakIndex] = streakSize;
                } else {
                    streakSize = 0;
                    if (streaks[streakIndex] != 0) {
                        streakIndex++;
                        streaks[streakIndex] = 0;
                    }
                }
            }
        }
    }

    public int biggestStreak(){
        int biggestStreak=0;

        for (int index = 0; index <= streakIndex; index++) {
            if (streaks[index] > biggestStreak) {
                biggestStreak = streaks[index];
            }
        }
        return biggestStreak;
    }

    public int latestStreak(){
        return streaks[streakIndex];
    }

    public int noMistake() {
        return noMistake;
    }
}
