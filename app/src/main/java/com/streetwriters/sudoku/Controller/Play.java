package com.streetwriters.sudoku.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.streetwriters.sudoku.Controller.OnClick.GridOnClick;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.GridLayout;


public class Play extends UseGameState {
    Context context;
    int[][] onPauseArray;

    public Play(Context context) {
        this.context = context;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void onPause() {
        int[][] solved = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                CellLayout cellLayout = new CellLayout(context, Integer.parseInt(i + "" + j));

                if (isClue(i,j)) {
                    solved[i][j] = 0;
                } else {
                    if (cellLayout.isCellFilled()) {
                        solved[i][j] = Integer.parseInt(cellLayout.getCellText());
                    } else {
                        solved[i][j] = 0;
                    }
                }

                cellLayout.setCellText("");

                if (getNote(i,j) != null) {
                    if (cellLayout.getCell() != null) {
                        for (int index = 0; index < getNote(i,j).size(); index++) {
                            int idNumber = getNote(i,j).get(index);
                            cellLayout.eraseNote(idNumber);
                        }
                    }
                }
            }
        }

        new GridLayout(context).getGrid().setOnTouchListener(null);
        ((Activity) context).findViewById(R.id.pause_screen).setVisibility(View.VISIBLE);
        onPauseArray = solved;
    }

    public void onResume() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                CellLayout cellLayout = new CellLayout(context, Integer.parseInt(i + "" + j));

                if (isClue(i,j)) {
                    cellLayout.setCellText("" + gameState.getUnSolvedPuzzle()[i][j]);
                }

                if (onPauseArray[i][j] != 0) {
                    cellLayout.setCellText("" + onPauseArray[i][j]);
                }

                if (getNote(i,j)!= null) {
                    if (getNote(i,j).size() != 0 &&
                            cellLayout.getNote() == null) {
                        cellLayout.showNote();
                        cellLayout.hideCell();
                    }

                    if (cellLayout.getNote() != null) {
                        for (int index = 0; index < getNote(i,j).size(); index++) {
                            int idNumber = getNote(i,j).get(index);
                            cellLayout.setNoteText(idNumber);
                        }
                    }
                }
            }
        }

        ((Activity) context).findViewById(R.id.grid).setOnTouchListener(new GridOnClick());
        ((Activity) context).findViewById(R.id.pause_screen).setVisibility(View.GONE);
    }
}
