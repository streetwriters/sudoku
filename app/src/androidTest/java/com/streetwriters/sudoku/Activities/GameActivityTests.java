package com.streetwriters.sudoku.Activities;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.streetwriters.sudoku.Utils.Global;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameActivityTests {
    @Rule
    public final ActivityTestRule<GameActivity> mActivityRule =
            new ActivityTestRule<>(GameActivity.class, true, false);


    @Test
    public void test_Array(){

        Intent intent = new Intent();

        intent.putExtra("difficulty",1);

        mActivityRule.launchActivity(intent);

        assertEquals(9, Global.getSolvedPuzzle().length);
    }
}
