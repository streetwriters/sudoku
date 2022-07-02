package com.streetwriters.sudoku.View.Layouts;

import android.view.View;
import android.widget.TextView;

import com.streetwriters.sudoku.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StatsLayout {
    View view;
    public StatsLayout(View view){
        this.view = view;
    }

    public void setGamesStarted(int gamesStarted){
        ((TextView)view.findViewById(R.id.games_started_stat)).setText(String.valueOf(gamesStarted));
    }

    public void setGamesWon(int gamesWon){
        ((TextView)view.findViewById(R.id.games_won_stat)).setText(String.valueOf(gamesWon));
    }

    public void setWinRate(double winRate){
        DecimalFormat df = new DecimalFormat("#.#%");
        df.setRoundingMode(RoundingMode.CEILING);
        ((TextView)view.findViewById(R.id.win_rate_stat)).setText(df.format(winRate));
    }

    public void setWeeklyWinRate(double weeklyWinRate){
        DecimalFormat df = new DecimalFormat("#.#%");
        df.setRoundingMode(RoundingMode.CEILING);
        ((TextView)view.findViewById(R.id.winrate_weekly_stat)).setText(df.format(weeklyWinRate));
    }

    public void setBestTime(double bestTime){
        ((TextView)view.findViewById(R.id.best_time_stat)).setText(String.valueOf(bestTime));
    }

    public void setAverageTime(double averageTime){
        ((TextView)view.findViewById(R.id.average_time_stat)).setText(String.valueOf(averageTime));
    }

    public void setBestScore(double bestScore){
        ((TextView)view.findViewById(R.id.best_score_stat)).setText(String.valueOf(bestScore));
    }

    public void setAverageScore(double averageScore){
        ((TextView)view.findViewById(R.id.best_time_stat)).setText(String.valueOf(averageScore));
    }

    public void setCurrentWinStreak(int currentWinStreak){
        ((TextView)view.findViewById(R.id.current_win_stat)).setText(String.valueOf(currentWinStreak));
    }

    public void setBestWinStreak(int bestWinStreak){
        ((TextView)view.findViewById(R.id.best_win_stat)).setText(String.valueOf(bestWinStreak));
    }

    public void setNoMistakeStreak(int noMistakeStreak){
        ((TextView)view.findViewById(R.id.wins_no_mistakes_stat)).setText(String.valueOf(noMistakeStreak));
    }
}
