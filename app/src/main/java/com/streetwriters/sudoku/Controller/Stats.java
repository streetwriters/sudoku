package com.streetwriters.sudoku.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Functions.Objects.DailyChallengeClass;
import com.streetwriters.sudoku.Functions.Objects.Games;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Stats {
    int noOfMistakes;
    Activity activity;
    int TOATL_SCORE = 88;
    Games games;
    int result;

    public Stats(int noOfMistakes, Activity activity, int result) {
        this.activity = activity;
        this.noOfMistakes = noOfMistakes;
        this.result = result;
        games = new Games();
        games.setDifficulty(getGameDifficulty());
        games.setGameNo(getGameNumber());
        games.setMistakes(noOfMistakes);
        games.setResult(result);
        games.setScore(getTotalScore());
        games.setTime(getTimeinMinutes());

        if (getGameDifficulty() == 4) {
            games.setDailyChallengeClass(getDailyChallengeClass());
        }

        saveStats();
    }

    public double getTotalScore() {
        double Score = TOATL_SCORE - (noOfMistakes * 2) - (getTimeinMinutes() * 0.1);
        return Score;
    }

    public int getTimeinMinutes() { //gamestate.timer instead of textview
        String timer = ((TextView)activity.findViewById(R.id.timer)).getText().toString();
        String[] hourMin = timer.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
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
        try {
            File path = activity.getFilesDir();
            File file = new File(path, "main.dat");
            ArrayList<Games> GamesList = new ArrayList<>();

            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                GamesList = (ArrayList<Games>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                Log.d("test", e.getMessage());
            }

            //Log.d("test","testing"+getContext().getFilesDir());
            //Log.d("test","Day\t"+testDailyChallenge.getDay()+"\tMonth\t"+testDailyChallenge.getMonth()+"\tYear\t"+testDailyChallenge.getYear());
            GamesList.add(games);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(GamesList);
            oos.close();

            for (int index = 0; index < GamesList.size(); index++) {
                Log.d("test", "Difficulty:" + GamesList.get(index).getDifficulty() + "\nGame NO:" + GamesList.get(index).getGameNo() + "\nMistakes: " + GamesList.get(index).getMistakes() + "\nGameResult:" + GamesList.get(index).getResult()
                        + "\nScore: " + GamesList.get(index).getScore() + "\nTime: " + GamesList.get(index).getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        }
    }
}
