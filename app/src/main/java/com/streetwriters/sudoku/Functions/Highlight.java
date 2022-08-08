package com.streetwriters.sudoku.Functions;


import android.graphics.Color;
import android.view.View;

import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

import java.util.ArrayList;

public class Highlight {
    int id;
    View view;
    Selection selection = new Selection();
    GameState gameState = GameState.getInstance();

    public Highlight(int id, View view) {
        this.id = id;
        this.view = view;
    }

    public void apply() {
        column();
        row();
        square();
        remove();
    }

    public void square() {
        int squareId = selection.square(id);
        //Log.d("Highlight", "square: id "+id+" squareId: "+squareId);
        int length = gameState.getBoxes()[squareId].size();
        highlight(length, gameState.getBoxes()[squareId]);
    }

    public void column() {
        int col = selection.column(id);
        //Log.d("Highlight", "column: col: "+id);
        //Log.d("Highlight", "column: id: "+col);
        int length = gameState.getColumns()[col].size();
        highlight(length, gameState.getColumns()[col]);
    }

    public void row() {
        int row = selection.row(id);
        int length = gameState.getRowIds()[row].size();
        highlight(length, gameState.getRowIds()[row]);
    }

    private void highlight(int selectionLength, ArrayList<Integer> gridGroup) {
        for (int index = 0; index < selectionLength; index++) {
            int cellId = gridGroup.get(index);
            CellLayout cellLayout = new CellLayout(view.getContext(), cellId);

            if (selection.isCellUnselected(cellId)) {
                cellLayout.setSelectedCellBackground();
                cellLayout.setSelectedCellViewBackground();
                addCellToMemory(cellId);
            }
        }
    }

    public void remove() {
        removeHighlight();
        removeMatchingCells();
        refreshCellMemory();
    }

    private void removeHighlight() {
        int activeCell = gameState.getActiveCellId();

        if (gameState.previousHighlightedCells() != null) {
            CellLayout activeCellLayout = new CellLayout(view.getContext(), activeCell);

            if (selection.isActiveCellPresent(activeCell)) {
                activeCellLayout.setSelectedCellBackground();
                activeCellLayout.setSelectedCellViewBackground();
                activeCellLayout.setCellTextColor(R.attr.colorAccent);
                activeCellLayout.setNotesTextColor(R.attr.colorAccent);
            } else {
                activeCellLayout.setCellTextColor(R.attr.colorAccent);
                activeCellLayout.setNotesTextColor(R.attr.colorAccent);
            }

            for (int index = 0; index < gameState.previousHighlightedCells().size(); index++) {
                int id = gameState.previousHighlightedCells().get(index);
                if (selection.isCellAbsent(id)) {
                    CellLayout cellLayout = new CellLayout(view.getContext(), id);
                    cellLayout.setCellBackground();
                    cellLayout.setCellViewBackground();
                }
            }
        }
    }

    private void refreshCellMemory() {
        gameState.setPreviousHighlightedCells(gameState.currentHighlightedCells());
        gameState.setCurrentHighlightedCells(new ArrayList<>());
    }

    private void removeMatchingCells() {
        if (gameState.getActiveMatchingCells().size() != 0) {
            for (int index = 0; index < gameState.getActiveMatchingCells().size(); index++) {
                new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setFixedCellBackground();
//                Digits digits = new Dimensions().numberToDigits(gameState.getActiveMatchingCells().get(index));
//                if (gameState.getUnSolvedPuzzle()[digits.first()][digits.second()] == 0)
//                    new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setSelectedCellBackground();
//                else
//                    new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setFixedCellBackground();
            }
            gameState.setActiveMatchingCells(new ArrayList<>());
        }
    }

    public void activeMatchingCells() {
        for (int index = 0; index < gameState.getActiveMatchingCells().size(); index++) {
            new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setMatchingCellBackground();
        }
    }


    void addCellToMemory(int cellId) {
        gameState.currentHighlightedCells().add(cellId);
    }
}
