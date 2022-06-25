package com.streetwriters.sudoku.Activities.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.streetwriters.sudoku.Activities.GameActivity;
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        ConstraintLayout constraintLayout = view.findViewById(R.id.home_fragment);
        Button mainGameBtn = view.findViewById(R.id.main_new_game);
        registerForContextMenu(mainGameBtn);

        mainGameBtn.setOnClickListener((View) -> {
            View.showContextMenu();
        });

        mainGameBtn.setOnLongClickListener(view1 -> true);
        Button resumeBtn = view.findViewById(R.id.main_resume_game);

        try {
            ResumePuzzle resumePuzzle = new Data().loadGameFile();
            resumeBtn.setVisibility(View.VISIBLE);
            resumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, GameActivity.class);
                    intent.putExtra("difficulty", 5);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            resumeBtn.setVisibility(View.GONE);
        }

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
}
