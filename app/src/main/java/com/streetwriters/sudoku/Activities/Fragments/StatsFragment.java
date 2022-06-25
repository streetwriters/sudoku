package com.streetwriters.sudoku.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Objects.Games;
import com.streetwriters.sudoku.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatsFragment extends Fragment {
    ArrayList<Games> GamesList = new ArrayList<>();

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
        double GamesStarted = 0;
        double GamesWon = 0;
        double bestTimeRate = 0;
        double GamesWonTime = 0;
        double score = 0;
        double scoreSum = 0;
        int noMistakes = 0;
        int[] TotalStreaks = new int[100];
        int streakNO = 0;
        int arrayNo = 0;
        int BiggestStreak = 0;
        int LatestStreak = TotalStreaks[arrayNo];
        double GamesStartedLastWeek = 0;
        double GamesWonLastWeek = 0;

        TotalStreaks[arrayNo] = 0;
        setTitle();
        setGamesList();

        for (int index = 0; index < GamesList.size(); index++) {
            GamesStarted++;

            if (GamesList.get(index).getMistakes() == 0) { //streaks should be seperate and in the functions
                streakNO++;
                TotalStreaks[arrayNo] = streakNO;
            } else {
                streakNO = 0;
                if (TotalStreaks[arrayNo] == 0) {
                } else {
                    arrayNo++;
                    TotalStreaks[arrayNo] = 0;
                }
            }

            if (GamesList.get(index).getResult() == 1) {
                GamesWon++;
                GamesWonTime = GamesList.get(index).getTime() + GamesWonTime;
                scoreSum = scoreSum + GamesList.get(index).getScore();

                if (score < GamesList.get(index).getScore()) {
                    score = GamesList.get(index).getScore();
                }
            }

            if (bestTimeRate > GamesList.get(index).getTime()) {
                bestTimeRate = GamesList.get(index).getTime();
            }

            if (GamesList.get(index).getMistakes() == 0) {
                noMistakes++;
            }
        }


        for (int index = 0; index <= arrayNo; index++) {
            if (TotalStreaks[index] > BiggestStreak) {
                BiggestStreak = TotalStreaks[index];
            }
        }


        for (int index = (GamesList.size() - 1); index > -1; index--) {
            GamesStartedLastWeek++;

            if (GamesList.get(index).getResult() == 1) {
                GamesWonLastWeek++;
            }

            if (GamesStartedLastWeek == 7) {
                break;
            }
        }

        double winRatePrecent = Double.isNaN(GamesWon / GamesStarted) ? 0 : (GamesWon / GamesStarted);
        double averageTimeWon = Double.isNaN(GamesWonTime / GamesWon) ? 0 : (GamesWonTime / GamesWon);
        double TotalaverageScore = Double.isNaN(scoreSum / GamesWon) ? 0 : (scoreSum / GamesWon);
        double weeklyWinRate = Double.isNaN(GamesWonLastWeek / GamesStartedLastWeek) ? 0 : (GamesWonLastWeek / GamesStartedLastWeek);

        TextView gamesStarted = view.findViewById(R.id.games_started_stat);
        gamesStarted.setText("" + GamesStarted);
        TextView gamesWon = view.findViewById(R.id.games_won_stat);
        gamesWon.setText("" + GamesWon);
        DecimalFormat df = new DecimalFormat("#.#%");
        df.setRoundingMode(RoundingMode.CEILING);
        TextView winRate = view.findViewById(R.id.win_rate_stat);
        winRate.setText(df.format(winRatePrecent));
        TextView winRateWeekly = view.findViewById(R.id.winrate_weekly_stat);
        winRateWeekly.setText(df.format(weeklyWinRate));
        TextView bestTime = view.findViewById(R.id.best_time_stat);
        bestTime.setText("" + bestTimeRate);
        TextView averageTime = view.findViewById(R.id.average_time_stat);
        averageTime.setText("" + averageTimeWon);
        TextView bestScore = view.findViewById(R.id.best_score_stat);
        bestScore.setText("" + score);
        TextView averageScore = view.findViewById(R.id.average_score_stat);
        averageScore.setText("" + TotalaverageScore);
        TextView currentWinStreak = view.findViewById(R.id.current_win_stat);
        currentWinStreak.setText("" + LatestStreak);
        TextView bestWinStreak = view.findViewById(R.id.best_win_stat);
        bestWinStreak.setText("" + BiggestStreak);
        TextView noMistakeStreak = view.findViewById(R.id.wins_no_mistakes_stat);
        noMistakeStreak.setText("" + noMistakes);

        return view;
    }

    private void setTitle() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Statistics");
    }

    private void setGamesList() {
        try {
            Data data = new Data();
            GamesList = data.getGameStats();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
