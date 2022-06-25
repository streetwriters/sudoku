package com.streetwriters.sudoku.Functions;

import com.streetwriters.sudoku.Functions.Objects.HistoryItem;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

public class History {
    HistoryItem historyItem;
    GameState gameState = GameState.getInstance();

    public History(){
        historyItem = new HistoryItem();
    }

    public void setNoteHistory(int text){
        historyItem.setNoteText(Integer.toString(text));
        historyItem.setCellId(gameState.getActiveCellId());
        historyItem.setIsNote(true);
        historyItem.setNoteId(new Dimensions().stringToId("note_" + text));
        gameState.getUserHistory().add(historyItem);
    }

    public void setCellHistory(CellLayout cellLayout){
        historyItem.setCellText(cellLayout.getCellText());
        historyItem.setCellId(gameState.getActiveCellId());
        gameState.getUserHistory().add(historyItem);
    }
}
