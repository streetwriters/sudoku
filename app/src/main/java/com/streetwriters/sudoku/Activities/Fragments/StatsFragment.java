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

//        double GamesStarted = 0;
//        double GamesWon = 0;
//        double bestTimeRate = 0;
//        double GamesWonTime = 0;
//        double bestScore = 0;
//        double scoreSum = 0;
//        int noMistakes = 0;
//        int[] streaks = new int[100];
//        int streakSize = 0;
//        int streakIndex = 0;
//
//        int BiggestStreak = 0;
//        int LatestStreak = streaks[streakIndex];
//        double GamesStartedLastWeek = 0;
//        double GamesWonLastWeek = 0;
//
//        streaks[streakIndex] = 0;
//
//        setTitle();
//        setGamesList();
//
//        for (int index = 0; index < gameList.size(); index++) {
//            GamesStarted++;
//            Games games = gameList.get(index);
//
//            if (games.getMistakes() == 0) { //adding a number to a streak
//                streakSize++;
//                streaks[streakIndex] = streakSize;
//            } else {
//                streakSize = 0;
//                if (streaks[streakIndex] == 0) {
//                } else {
//                    streakIndex++;
//                    streaks[streakIndex] = 0;
//                }
//            }
//
//            if (games.getResult() == 1) {
//                GamesWon++;
//                GamesWonTime = games.getTime() + GamesWonTime;
//                scoreSum = scoreSum + games.getScore();
//
//                if (bestScore < games.getScore()) {
//                    bestScore = games.getScore();
//                }
//            }
//
//            if (bestTimeRate > games.getTime()) {
//                bestTimeRate = games.getTime();
//            }
//
//            if (games.getMistakes() == 0) {
//                noMistakes++;
//            }
//        }
//
//
//        for (int index = 0; index <= streakIndex; index++) {
//            if (streaks[index] > BiggestStreak) {
//                BiggestStreak = streaks[index];
//            }
//        }
//
//
//        for (int index = (gameList.size() - 1); index > -1; index--) {
//            GamesStartedLastWeek++;
//
//            if (gameList.get(index).getResult() == 1) {
//                GamesWonLastWeek++;
//            }
//
//            if (GamesStartedLastWeek == 7) {
//                break;
//            }
//        }
//
//        double winRatePrecent = Double.isNaN(GamesWon / GamesStarted) ? 0 : (GamesWon / GamesStarted);
//        double averageTimeWon = Double.isNaN(GamesWonTime / GamesWon) ? 0 : (GamesWonTime / GamesWon);
//        double TotalaverageScore = Double.isNaN(scoreSum / GamesWon) ? 0 : (scoreSum / GamesWon);
//        double weeklyWinRate = Double.isNaN(GamesWonLastWeek / GamesStartedLastWeek) ? 0 : (GamesWonLastWeek / GamesStartedLastWeek);
//
//        TextView gamesStarted = view.findViewById(R.id.games_started_stat);
//        gamesStarted.setText("" + GamesStarted);
//        TextView gamesWon = view.findViewById(R.id.games_won_stat);
//        gamesWon.setText("" + GamesWon);
//        DecimalFormat df = new DecimalFormat("#.#%");
//        df.setRoundingMode(RoundingMode.CEILING);
//        TextView winRate = view.findViewById(R.id.win_rate_stat);
//        winRate.setText(df.format(winRatePrecent));
//        TextView winRateWeekly = view.findViewById(R.id.winrate_weekly_stat);
//        winRateWeekly.setText(df.format(weeklyWinRate));
//        TextView bestTime = view.findViewById(R.id.best_time_stat);
//        bestTime.setText("" + bestTimeRate);
//        TextView averageTime = view.findViewById(R.id.average_time_stat);
//        averageTime.setText("" + averageTimeWon);
//        TextView BestScore = view.findViewById(R.id.best_score_stat);
//        BestScore.setText("" + bestScore);
//        TextView averageScore = view.findViewById(R.id.average_score_stat);
//        averageScore.setText("" + TotalaverageScore);
//        TextView currentWinStreak = view.findViewById(R.id.current_win_stat);
//        currentWinStreak.setText("" + LatestStreak);
//        TextView bestWinStreak = view.findViewById(R.id.best_win_stat);
//        bestWinStreak.setText("" + BiggestStreak);
//        TextView noMistakeStreak = view.findViewById(R.id.wins_no_mistakes_stat);
//        noMistakeStreak.setText("" + noMistakes);

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
