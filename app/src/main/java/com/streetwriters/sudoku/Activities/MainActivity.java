package com.streetwriters.sudoku.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.streetwriters.sudoku.Activities.Fragments.HomeFragment;
import com.streetwriters.sudoku.Activities.Fragments.StatsFragment;
import com.streetwriters.sudoku.Activities.Fragments.TrophyFragment;
import com.streetwriters.sudoku.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setHomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id ==R.id.settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.option_home) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFrameLayout, new HomeFragment(MainActivity.this))
                            .commit();
                    return true;
                } else if (id == R.id.option_daily_challenge) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFrameLayout, new TrophyFragment())
                            .commit();
                    return true;
                } else if (id == R.id.option_stats) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFrameLayout, new StatsFragment())
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    void setHomeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainFrameLayout, new HomeFragment(this));
        ft.commit();
        setBottomNavigationBar();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}
