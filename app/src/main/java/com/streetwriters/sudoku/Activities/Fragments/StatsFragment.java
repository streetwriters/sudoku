package com.streetwriters.sudoku.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Objects.Game;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Layouts.StatsLayout;

import java.util.ArrayList;

public class StatsFragment extends Fragment {
    ArrayList<Game> gameList = new ArrayList<>();

    public StatsFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.stats_fragment, container, false);

        setTitle();
        setGamesList();
        GamesWon gamesWon = new GamesWon(gameList);
        Streak streak = new Streak(gameList);
        StatsLayout statsLayout = new StatsLayout(view);
        statsLayout.setGamesStarted(gamesWon.gamesStarted());
        statsLayout.setGamesWon(gamesWon.gamesWon());
        statsLayout.setWinRate(gamesWon.winRate());
        statsLayout.setWeeklyWinRate(gamesWon.weeklyWinRate());
        statsLayout.setBestTime(gamesWon.bestTime());
        statsLayout.setAverageTime(gamesWon.averageTime());
        statsLayout.setBestScore(gamesWon.bestScore());
        statsLayout.setAverageScore(gamesWon.averageScore());
        statsLayout.setCurrentWinStreak(streak.latestStreak());
        statsLayout.setBestWinStreak(streak.biggestStreak());
        statsLayout.setNoMistakeStreak(streak.noMistake());

        return view;
    }

    private void setTitle() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Statistics");
    }

    private void setGamesList() {
        try {
            Data data = new Data();
            gameList = data.getGameStats();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
