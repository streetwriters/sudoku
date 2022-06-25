package com.streetwriters.sudoku.Functions;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
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

    public void remove() {
        removeHighlight();
        removeMatchingCells();
        refreshCellMemory();
    }

    private void removeHighlight() {
        int activeCell = gameState.getActiveCellId();
        Log.d(Highlight.class.getSimpleName(), "removeHighlight: id: "+activeCell); //highlight does not work when matching cells

        if (gameState.getHighlightedCells()[0] != null) {
            CellLayout activeCellLayout = new CellLayout(view.getContext(), activeCell);

            if (selection.isActiveCellPresent(activeCell)) {
                activeCellLayout.setSelectedCellBackground();
                activeCellLayout.setSelectedCellViewBackground();
                activeCellLayout.setCellTextColor(Color.BLACK);
                activeCellLayout.setNotesTextColor(Color.BLACK);
            }else{
                activeCellLayout.setCellTextColor(Color.BLACK);
                activeCellLayout.setNotesTextColor(Color.BLACK);
            }

            for (int index = 0; index < gameState.getHighlightedCells()[0].size(); index++) {
                int id = gameState.getHighlightedCells()[0].get(index);
                if (selection.isCellAbsent(id)) {
                    CellLayout cellLayout = new CellLayout(view.getContext(), id);
                    cellLayout.setCellBackground();
                    cellLayout.setCellViewBackground();
                }
            }
        }
    }

    private void refreshCellMemory() {
        gameState.getHighlightedCells()[0] = gameState.getHighlightedCells()[1];
        gameState.getHighlightedCells()[1] = new ArrayList<>();
    }

    private void removeMatchingCells() {
        //Log.d("HighLight", "removeMatchingNumbers: "+singleton.getActiveValueMatchingIds().size());
        if (gameState.getActiveMatchingCells().size() != 0) {
            for (int index = 0; index < gameState.getActiveMatchingCells().size(); index++)
                new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setFixedCellBackground();
            gameState.setActiveMatchingCells(new ArrayList<>());
        }
    }

    public void activeMatchingCells(){
        //if (singleton.getActiveMatchingCells().size() != 0)
        for (int index = 0; index < gameState.getActiveMatchingCells().size(); index++) {
            new CellLayout(view.getContext(), gameState.getActiveMatchingCells().get(index)).setMatchingCellBackground();
        }
    }

    private void highlight(int selectionLength, ArrayList<Integer> gridGroup) {
        for (int index = 0; index < selectionLength; index++) {
            int cellId = gridGroup.get(index);
            CellLayout cellLayout = new CellLayout(view.getContext(), cellId);
            //Log.d("Highlight", "highlight: context: " + view.getContext() + " id: " + cellId);

            if (selection.isCellUnselected(cellId)) {
                cellLayout.setSelectedCellBackground();
                cellLayout.setSelectedCellViewBackground();
                addCellToMemory(cellId);
            }
        }
    }

    void addCellToMemory(int cellId) {
        if (gameState.getHighlightedCells()[1] != null) {
            gameState.getHighlightedCells()[1].add(cellId);
        } else {
            gameState.getHighlightedCells()[1] = new ArrayList<>();
            gameState.getHighlightedCells()[1].add(cellId);
        }
    }
}
