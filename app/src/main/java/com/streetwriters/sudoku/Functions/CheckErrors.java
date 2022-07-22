package com.streetwriters.sudoku.Functions;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;

import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.TextLayout;

public class CheckErrors {
    GameState gameState = GameState.getInstance();
    int[][] solvedPuzzle = gameState.getSolvedPuzzle();
    int[][] userSolvedPuzzle = gameState.getUserSolvedPuzzle();
    Dimensions changeUnits = new Dimensions();

    public void check(Activity activity) {
        Boolean error = checkActiveCellError(activity);
        CellLayout cellLayout = new CellLayout(activity, gameState.getActiveCellId());
        //Log.d(CheckErrors.class.getSimpleName(), "check: error: " + error);
        if (error) {
            cellLayout.setCellTextColor(Color.RED);
            new TextLayout(activity).addMistake();
        } else {
            ColorStateList colorList = cellLayout.getCell().getTextColors();
            //Log.d(CheckErrors.class.getSimpleName(), "check: colorLisr: " + colorList.getDefaultColor() + " Color Red: " + Color.RED);
            if (colorList.getDefaultColor() == Color.RED)
                cellLayout.getCell().setTextColor(Color.WHITE);
        }
    }

    public void checkAllErrors(Activity activity) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (userSolvedPuzzle[i][j] != 0 && userSolvedPuzzle[i][j] != solvedPuzzle[i][j]) {
                    new CellLayout(activity, Integer.parseInt(i + "" + j)).setCellErrorColor();
                }//inner if

            }//inner for

        }//outer for

    }//end method

    public Boolean checkActiveCellError(Context context) {
        Digits digits = changeUnits.numberToDigits(gameState.getActiveCellId());
        CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());
        //Log.d(CheckErrors.class.getName(), "checkActiveCellError: "+cellLayout);
        int activeCellText = Integer.parseInt(cellLayout.getCellText());
        return solvedPuzzle[digits.first()][digits.second()] != activeCellText;
    }

}
