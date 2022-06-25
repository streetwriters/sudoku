package com.streetwriters.sudoku.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

public class FillCells extends UseGameState {
    Resources r;
    String name;
    Context context;

    public FillCells(Context context) {
        this.context = context;
        r = context.getResources();
        name = context.getPackageName();
    }

    public FillCells() {
    }

    public void fill(Activity activity) {
        r = activity.getResources();
        name = activity.getPackageName();
        this.context = activity;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid(i, j);
    }

    public void grid(int i, int j) {
        if (getNote(i, j) != null) {
            CellLayout cellLayout = new CellLayout(context, Integer.parseInt(i + "" + j));

            if (getNote(i, j).size() != 0 &&
                    cellLayout.getNote() == null) {
                cellLayout.showNote();
                cellLayout.hideCell();
            }

            if (cellLayout.getNote() != null) {
                for (int index = 0; index < getNote(i, j).size(); index++) {
                    int idNumber = getNote(i, j).get(index);
                    cellLayout.setNoteText(idNumber);
                }
            }
        }
    }
}
