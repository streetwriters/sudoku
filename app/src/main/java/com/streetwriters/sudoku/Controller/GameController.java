package com.streetwriters.sudoku.Controller;

import android.app.Activity;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Singletons.LoadGameState;
import com.streetwriters.sudoku.Functions.CheckErrors;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.EditPadLayout;
import com.streetwriters.sudoku.View.Layouts.GridLayout;
import com.streetwriters.sudoku.View.Layouts.NumPadLayout;
import com.streetwriters.sudoku.View.Layouts.TextLayout;
import com.streetwriters.sudoku.View.Ui.BottomLayout;

public class GameController extends UseGameState {
    public Activity activity;
    private int GRID_ID = 100000;
    GameState gameState = GameState.getInstance();

    public GameController(Activity activity) {
        this.activity = activity;
        new LoadGameState(activity).game();
        new TextLayout(activity).arrange();
        LinearLayout gameLayout = activity.findViewById(R.id.game_layout);
        gameLayout.addView(new GridLayout(activity).newArrangement());
        BottomLayout bottomLayout = new BottomLayout(activity);
        gameLayout.addView(bottomLayout);
        bottomLayout.addView(new EditPadLayout(activity).arrange());
        bottomLayout.addView(new NumPadLayout(activity).arrange());

        if (new LoadGameState(activity).getGameDifficulty()==5) { // ths should be moved to suduko views
            FillCells fillCells = new FillCells();
            fillCells.fill(activity);
            CheckAllErrors();
        }
        ////////////////////////////////////////////////////////////
        ///////////TESTING/////////////////////////////////////////
        //////////////////////////////////////////////////////////
        //GameActivityTests tests= new GameActivityTests();
        //tests.RunTests();
        //TestingClass testingClass = new TestingClass(singleton.getUnSolvedPuzzle());
    }

    void CheckAllErrors() {
        CheckErrors errors = new CheckErrors();
        errors.checkAllErrors(activity);
    }


    private void TestingPuzzle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                CellLayout cellLayout = new CellLayout(activity, Integer.parseInt(i + "" + j));

                if (isClue(i,j)) {
                } else {
                    if (i == 8 && j == 8) {
                        break;
                    }
                    cellLayout.setCellText("" + gameState.getSolvedPuzzle()[i][j]);
                }
            }
        }
        gameState.setUserSolvedPuzzle(gameState.getSolvedPuzzle());
    }
}
