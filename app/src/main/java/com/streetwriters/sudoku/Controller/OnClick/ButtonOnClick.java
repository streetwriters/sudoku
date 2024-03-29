package com.streetwriters.sudoku.Controller.OnClick;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.streetwriters.sudoku.Controller.Dialogs.GameCompleteDialog;
import com.streetwriters.sudoku.Functions.CellGroups;
import com.streetwriters.sudoku.Functions.CheckErrors;
import com.streetwriters.sudoku.Functions.GameCompletion;
import com.streetwriters.sudoku.Functions.History;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

import java.util.ArrayList;

public class ButtonOnClick extends UseGameState {
    Context context;

     public ButtonOnClick(Context context) {
        this.context = context;
    }

    public void erase() {
        if (!isActiveCellClue()) {
            CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());

            if (cellLayout.isNotePresent() && cellLayout.isNoteVisible()) {
                eraseNoteAll(cellLayout);
            } else {
                eraseCell(cellLayout);
            }
        }
    }

    public void eraseNoteAll(CellLayout cellLayout) {
        ArrayList<Integer> note= getActiveNote();
        for (int index = 0; index < note.size(); index++) {
            int id = note.get(index);
            cellLayout.eraseNote(id);
            note.remove(index);
        }
    }

    protected void eraseNote(Integer noteText) {
        CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());
        if (gameState.getActiveCellId()>-1) {
            cellLayout.eraseNote(noteText);
            removeActiveNoteElement(noteText);
        }
    }

    private void eraseCell(CellLayout cellLayout) {
        if (cellLayout.isCellPresent())
            if (cellLayout.isCellFilled()) {
                new CellGroups().removeMatchingCell(Integer.parseInt(cellLayout.getCellText()), gameState.getActiveCellId());
                cellLayout.eraseCell();
                gameState.updateUserSolvedPuzzle(0);
            }
    }

    public void cellClick(int text){
        Log.d(ButtonOnClick.class.getSimpleName(), "cellClick: id: "+gameState.getActiveCellId());
        CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());
        new History().setCellHistory(cellLayout);
        cellLayout.setCellText(Integer.toString(text));
        new CellGroups().addMatchingCell(text, gameState.getActiveCellId());
        gameState.updateUserSolvedPuzzle(text);
        checkErrors();
        checkGameCompletion();
    }

    void checkErrors(){
        CheckErrors checkErrors = new CheckErrors();
        checkErrors.check((Activity) context);
    }

    void checkGameCompletion(){
        GameCompletion completion = new GameCompletion();
        if (completion.isGameWon()) {
            GameCompleteDialog dialog = new GameCompleteDialog((Activity) context);
            dialog.Show();
        }
    }

    public void setButtonVisibility(CellLayout cellLayout) {
        if (gameState.isTakingNotes())
            showNotes(cellLayout);
        else
            showCell(cellLayout);
    }

    private void showNotes(CellLayout cellLayout) {
        Log.d(NumPadOnClick.class.getSimpleName(), "showNotes: "+ getActiveNoteSize());
        if (getActiveNoteSize() == 0) {
            cellLayout.showNote();
            cellLayout.hideCell();
            erase();
        }
    }

    private void showCell(CellLayout cellLayout) {
        if (getActiveNoteSize() != 0) {
            cellLayout.showCell();
            cellLayout.hideNote();
            erase();
        }
    }
}
