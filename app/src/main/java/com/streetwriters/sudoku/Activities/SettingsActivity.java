package com.streetwriters.sudoku.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.streetwriters.sudoku.BuildConfig;
import com.streetwriters.sudoku.Functions.Utils.ChangeTheme;
import com.streetwriters.sudoku.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeTheme.onActivityCreateSetTheme(this);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                //this.startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference share = findPreference("share");
            share.setOnPreferenceClickListener(preference ->{
                shareApp();
                return false;
            });

            Preference rate = findPreference("rate");
            rate.setOnPreferenceClickListener(preference ->{
                openLink("market://details?id=" +  getActivity().getPackageName());
                return false;
            });

            Preference privacy = findPreference("privacy");
            privacy.setOnPreferenceClickListener(preference ->{
                openLink("https://streetwriters.co/sudoku/privacy.html");
                return false;
            });

            Preference about = findPreference("about");
            about.setOnPreferenceClickListener(preference ->{
                Toast.makeText(getActivity(), R.string.about_summary, Toast.LENGTH_LONG).show();
                return false;
            });
        }

        void shareApp(){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sudoku");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                Log.d("TEST1", "shareApp: "+e.getMessage());
            }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //this.startActivity(new Intent(this, MainActivity.class));
    }
}