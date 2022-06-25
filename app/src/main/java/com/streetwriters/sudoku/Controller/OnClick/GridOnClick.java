package com.streetwriters.sudoku.Controller.OnClick;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.streetwriters.sudoku.Functions.CellGroups;
import com.streetwriters.sudoku.Functions.Highlight;
import com.streetwriters.sudoku.Functions.Position;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.View.Layouts.CellLayout;


public class GridOnClick extends UseGameState implements View.OnTouchListener {
    View view;
    int previousPositionX = 0;
    int previousPositionY = 0;
    String lastButtonText = "-1";

    public GridOnClick() {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Position position = new Position(view, motionEvent, 9, 9);
        this.view = view;
        if (!position.isUnchanged(previousPositionX, previousPositionY))
            if (position.isWithinLimits()) {
                if (isClue(position.Y(),position.X())) {
                    fixedCellClick(position.X(), position.Y());
                } else {
                    cellClick(position.X(), position.Y());
                }

                previousPositionX = position.X();
                previousPositionY = position.Y();
                return true;
            }
        return false;
    }

    private void cellClick(int positionX, int positionY) {
        int id = Integer.parseInt(String.valueOf(positionY) + positionX);
        Log.d(GridOnClick.class.getSimpleName(), "cellClick: ");
        Highlight highlight = new Highlight(id, view);
        highlight.apply();
        CellLayout cellLayout = new CellLayout(view.getContext(), id);
        cellLayout.setActiveCellBackground();
        cellLayout.setActiveCellViewBackground();
        cellLayout.setCellTextColor(Color.WHITE);
        //cellLayout.setNotesTextColor(Color.BLACK);
        gameState.setActiveCellId(id);
        cellLayout.setNotesTextColor(Color.WHITE);

        if (cellLayout.isCellFilled()&&cellLayout.isCellValueTrue()) {
            new CellGroups().updateActiveMatchingCells(Integer.parseInt(cellLayout.getCellText()));
            highlight.activeMatchingCells();
        }

        lastButtonText = "-1";
    }

    private void fixedCellClick(int x_button_number, int y_button_number) {
        int id = Integer.parseInt(String.valueOf(y_button_number) + x_button_number);

        CellLayout cellLayout = new CellLayout(view.getContext(), id);
        //Log.d("GridOnClick", "fixedCellClick: lastButtonText"+lastButtonText.equals(cellLayout.getCellText()));
        if (!lastButtonText.equals(cellLayout.getCellText())) {
            lastButtonText = cellLayout.getCellText();
            Highlight highlight = new Highlight(id, view);
            highlight.remove();
            cellLayout.setMatchingCellBackground();
            gameState.setActiveCellId(-1);
            new CellGroups().updateActiveMatchingCells(Integer.parseInt(cellLayout.getCellText()));
            highlight.activeMatchingCells();
        }
    }

}
