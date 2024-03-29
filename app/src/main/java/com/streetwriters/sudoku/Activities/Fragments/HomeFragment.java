package com.streetwriters.sudoku.Activities.Fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.streetwriters.sudoku.Activities.GameActivity;
import com.streetwriters.sudoku.Activities.Help;
import com.streetwriters.sudoku.Functions.Analytics;
import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Objects.ResumePuzzle;
import com.streetwriters.sudoku.R;

public class HomeFragment extends Fragment {
    Activity activity;

    public HomeFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);

        new Analytics().execute("/sudoku");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        LinearLayout linearLayout = view.findViewById(R.id.home_fragment);
        AppCompatButton mainGameBtn = view.findViewById(R.id.main_new_game);
        registerForContextMenu(mainGameBtn);

        mainGameBtn.setOnClickListener((View) -> {
            View.showContextMenu();
        });

        mainGameBtn.setOnLongClickListener(view1 -> true);

        AppCompatButton resumeBtn = view.findViewById(R.id.main_resume_game);
            resumeBtn.setVisibility(View.VISIBLE);
            resumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ResumePuzzle resumePuzzle = new Data().isResumeFilePresent();
                        Log.d(HomeFragment.class.getSimpleName(), "onCreateView: "+resumePuzzle);
                        Intent intent = new Intent(activity, GameActivity.class);
                        intent.putExtra("difficulty", 5);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(activity, "No games to resume", Toast.LENGTH_SHORT).show();
                        //resumeBtn.setVisibility(View.GONE);
                    }
                }
            });

        AppCompatButton rateButton = view.findViewById(R.id.main_rate_us);

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("market://details?id=" +  getActivity().getPackageName());
            }
        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.game_choice, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option_easy) {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 1);
            startActivity(intent);
        } else if (id == R.id.option_medium) {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 2);
            startActivity(intent);
        } else if (id == R.id.option_hard) {
            Intent intent = new Intent(activity, GameActivity.class);
            intent.putExtra("difficulty", 3);
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }

    private void openLink(String link) {
        Uri uri = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), " unable to find market app", Toast.LENGTH_LONG).show();
            Log.d("TEST1", "launchMarket: "+e.getMessage());
        }
    }
}
