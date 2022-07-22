package com.streetwriters.sudoku;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Root;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.espresso.Espresso.*;

import org.hamcrest.Matcher;
import org.jetbrains.annotations.TestOnly;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import com.streetwriters.sudoku.Activities.GameActivity;
import com.streetwriters.sudoku.Activities.MainActivity;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Buttons.Cell;
import com.streetwriters.sudoku.View.Ui.CellView;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class ExampleInstrumentedTest {

//    @Rule
//    public ActivityScenarioRule<MainActivity> mainActivityRule =
//            new ActivityScenarioRule<>(MainActivity.class);

    static Intent intent;

    static {
        intent = new Intent(ApplicationProvider.getApplicationContext(), GameActivity.class);
        intent.putExtra("difficulty", 2);
    }

    @Rule
    public ActivityScenarioRule<GameActivity> gameActivityActivityRule =
            new ActivityScenarioRule<GameActivity>(intent);

    int column = 8;
    @Test
    public void generalTest() {
        ActivityScenario scenario = gameActivityActivityRule.getScenario();
        //onView(withId(R.id.main_new_game)).perform(click());
        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        //onView(withText("MEDIUM")).perform(click());

        for (int i = 0; i < 9; i++) {
            if(GameState.getInstance().getUnSolvedPuzzle()[8][i]==0) {
                onView(withId(Integer.parseInt(8+""+i))).perform(click());
                column = i;
                break;
            }
        }
        onView(withId(R.id.num_9)).perform(click());
        final int i=column;
            scenario.onActivity(activity -> {
                CellView cellView = activity.findViewById(Integer.parseInt(8+""+column));
                Cell cell = cellView.findViewById(R.id.cell);
                assertEquals("9", cell.getText());
            });
        onView(withId(R.id.erase)).perform(click());
        onView(withId(44)).perform(click());
        onView(withId(R.id.hint)).perform(click());
        onView(withId(Integer.parseInt(8+""+column))).perform(click());
        onView(withId(R.id.take_notes)).perform(click());
        onView(withId(R.id.num_4)).perform(click());
        onView(withId(R.id.num_4)).perform(click());
        onView(withId(R.id.num_3)).perform(click());
        onView(withId(R.id.num_2)).perform(click());
        onView(withId(R.id.erase)).perform(click());
        onView(withId(R.id.num_8)).perform(click());
        onView(withId(R.id.hint)).perform(click());
        onView(withId(R.id.take_notes)).perform(click());
        onView(withId(R.id.hint)).perform(click());
        onView(withId(33)).perform(click());
        onView(withId(22)).perform(click());
        onView(withId(11)).perform(click());
        int seconds = Integer.parseInt(getText(withId(R.id.timer)).split(":")[2]);
        String timer;
        pressBack();
        onView(withText("RESUME GAME")).perform(click());
        onView(withId(33)).perform(click());
        int seconds2 = Integer.parseInt(getText(withId(R.id.timer)).split(":")[2]);
        Log.d("TIME_DIFFERENCE", "generalTest: seconds: " + seconds + " seconds2: " + seconds2);
        assertTrue(seconds < seconds2);
        onView(withId(R.id.menu_help)).perform(click());
        pressBack();
        pressBack();
        onView(withId(R.id.settings)).perform(click());
        pressBack();
    }

    @Test
    public void GameWon() {
        onView(withId(R.id.main_new_game)).perform(click());
        onView(withText("MEDIUM")).perform(click());

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                onView(withId(Integer.parseInt(i + "" + j))).perform(click());
                onView(withId(R.id.hint)).perform(click());
            }
        }

        onView(withId(00)).perform(click());
        onView(withId(R.id.hint)).perform(click());


        onView(withText("Back to main Page")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Rate Us")).perform(click());
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = {null};
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }
}
