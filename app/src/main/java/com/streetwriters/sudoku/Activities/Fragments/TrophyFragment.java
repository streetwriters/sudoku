package com.streetwriters.sudoku.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.streetwriters.sudoku.Activities.GameActivity;
import com.streetwriters.sudoku.Functions.Objects.DailyChallengeClass;
import com.streetwriters.sudoku.Functions.Objects.Game;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.threeten.bp.DayOfWeek;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class TrophyFragment extends Fragment {

    public TrophyFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ArrayList<Game> gameList = new ArrayList<>();
    int challengeCompleted = 0;
    MaterialCalendarView materialCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.trophy_fragment, container, false);

        setGamesList();
        setTitle();
        setMaterialCalendarView(view);
        setTitleAndChallengesEachMonth(view);
        materialCalendarView.setVisibility(View.VISIBLE);
        settingPlayButon(view);

        return view;
    }


    private void setTitle() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Daily Challenge");
    }


    private void setGamesList() {
        try {
            File path = getContext().getFilesDir();
            File file = new File(path, "main.dat");
            InputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameList = (ArrayList<Game>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setMaterialCalendarView(View view) {
        materialCalendarView = view.findViewById(R.id.calendarView);
        materialCalendarView.setTopbarVisible(false);
        TextView month = view.findViewById(R.id.month_of_calender);
        Dimensions units = new Dimensions();
        month.setText(units.monthToString(materialCalendarView.getCurrentDate().getMonth()) +
                " " + materialCalendarView.getCurrentDate().getYear());
        int CurrentYear = materialCalendarView.getCurrentDate().getYear();
        int CurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int DayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        int MinimumMonth = c.get(Calendar.MONTH) + 1; // beware of month indexing from zero
        int Minimumyear = c.get(Calendar.YEAR);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(Minimumyear, MinimumMonth, 1))
                .setMaximumDate(CalendarDay.from(CurrentYear, CurrentMonth, DayOfMonth))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                //.setSaveCurrentPosition(true)
                .commit();

        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
    }


    private void setTitleAndChallengesEachMonth(View view) {
        for (int index = 0; index < gameList.size(); index++) {
            if (gameList.get(index).getDifficulty() == 4) {
                int CurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                int CurrentYear = materialCalendarView.getCurrentDate().getYear();
                DailyChallengeClass dailyChallengeClass = gameList.get(index).getDailyChallengeClass();
                if ((dailyChallengeClass.getYear() == CurrentYear) && (dailyChallengeClass.getMonth() == CurrentMonth)) {
                    challengeCompleted++;
                }
            }
        }


        int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DATE);
        TextView challengesNo = view.findViewById(R.id.challenges_completed);
        Calendar calendar = Calendar.getInstance();
        TextView month = view.findViewById(R.id.month_of_calender);

        challengesNo.setText("" + challengeCompleted + "/" + lastDayOfMonth);

        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Dimensions units = new Dimensions();

                month.setText(units.monthToString(materialCalendarView.getCurrentDate().getMonth()) +
                        " " + materialCalendarView.getCurrentDate().getYear());
                challengeCompleted = 0;

                for (int index = 0; index < gameList.size(); index++) {
                    if (gameList.get(index).getDifficulty() == 4) {
                        DailyChallengeClass dailyChallengeClass = gameList.get(index).getDailyChallengeClass();
                        if ((dailyChallengeClass.getYear() == date.getYear()) && (dailyChallengeClass.getMonth() == date.getMonth())) {
                            challengeCompleted++;
                        }
                    }
                }

                calendar.set(Calendar.MONTH, date.getMonth() - 1);
                int lastDate = calendar.getActualMaximum(Calendar.DATE);
                challengesNo.setText("" + challengeCompleted + "/" + lastDate);
            }
        });
    }

    private void settingPlayButon(View view) {
        Button playChallenge = view.findViewById(R.id.trophy_button);
        int CurrentYear = materialCalendarView.getCurrentDate().getYear();
        int CurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int CurrentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        materialCalendarView.setSelectedDate(CalendarDay.from(CurrentYear, CurrentMonth, CurrentDay));
        playChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = materialCalendarView.getSelectedDate().getDay();
                int month = materialCalendarView.getSelectedDate().getMonth();
                int year = materialCalendarView.getSelectedDate().getYear();
                Boolean challengeCompleted = false;

                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("difficulty", 4);
                intent.putExtra("year", materialCalendarView.getSelectedDate().getYear());
                intent.putExtra("month", materialCalendarView.getSelectedDate().getMonth());
                intent.putExtra("day", materialCalendarView.getSelectedDate().getDay());

                for (int index = 0; index < gameList.size(); index++) {
                    if (gameList.get(index).getDifficulty() == 4) {
                        DailyChallengeClass dailyChallengeClass = gameList.get(index).getDailyChallengeClass();
                        if ((dailyChallengeClass.getDay() == day) && (dailyChallengeClass.getMonth() == month) && (dailyChallengeClass.getYear() == year)) {
                            Toast.makeText(getContext(), "You have already Completed this challenge", Toast.LENGTH_LONG).show();
                            challengeCompleted = true;
                        }
                    }
                }
/**/
                if (!challengeCompleted) {
                    startActivity(intent);
                }
            }
        });
    }
}
