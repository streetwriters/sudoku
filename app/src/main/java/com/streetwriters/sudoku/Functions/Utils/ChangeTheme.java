package com.streetwriters.sudoku.Functions.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import com.streetwriters.sudoku.Activities.GameActivity;
import com.streetwriters.sudoku.Functions.Utils.Singletons.LoadGameState;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.R;

public class ChangeTheme {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_NIGHT = 1;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        new AppState().setTheme(theme);
        new LoadGameState(activity).saveGame();
        activity.finish();
        if(activity.getClass().equals(GameActivity.class)) {
            Intent intent = new Intent(activity, activity.getClass());
            intent.putExtra("difficulty", 5);
            activity.startActivity(intent);
        }else{
            activity.startActivity(new Intent(activity,activity.getClass()));
        }
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(Activity activity) {

//        int nightModeFlags =
//                activity.getResources().getConfiguration().uiMode &
//                        Configuration.UI_MODE_NIGHT_MASK;
//        switch (nightModeFlags) {
//            case Configuration.UI_MODE_NIGHT_YES:
//                activity.setTheme(R.style.AppThemeDark_NoActionBar);
//                break;
//
//            case Configuration.UI_MODE_NIGHT_NO:
//                activity.setTheme(R.style.AppThemeLight_NoActionBar);
//                break;
//
//            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                setCustomTheme(activity);
//                break;
//        }
        setCustomTheme(activity);
    }

   private static void setCustomTheme(Activity activity){
        sTheme = new AppState().getTheme();

        switch (sTheme) {
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppThemeLight_NoActionBar);
                break;
            case THEME_NIGHT:
                activity.setTheme(R.style.AppThemeDark_NoActionBar);
                break;
        }
    }
}
