package com.streetwriters.sudoku.Controller.OnClick;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


import com.streetwriters.sudoku.Functions.History;
import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.View.Buttons.NumPadButton;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

public class NumPadOnClick extends ButtonOnClick {
    Context context;
    CellLayout cellLayout;

    public NumPadOnClick(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        int text = Integer.parseInt(((NumPadButton) view).getText().toString());

        if (!isActiveCellClue()) {
            cellLayout = new CellLayout(context, gameState.getActiveCellId());
            setButtonVisibility(cellLayout);

            if (gameState.isTakingNotes()) {
                switch (text) {
                    case 1:
                        noteClick(1);
                        break;
                    case 2:
                        noteClick(2);
                        break;
                    case 3:
                        noteClick(3);
                        break;
                    case 4:
                        noteClick(4);
                        break;
                    case 5:
                        noteClick(5);
                        break;
                    case 6:
                        noteClick(6);
                        break;
                    case 7:
                        noteClick(7);
                        break;
                    case 8:
                        noteClick(8);
                        break;
                    case 9:
                        noteClick(9);
                        break;
                }
            } else {
                cellClick(text);
            }
        } else {
            Toast.makeText(context, "Can't perform this Action.", Toast.LENGTH_SHORT).show();
        }
    }

    void noteClick(int identifier) {
        CellLayout cellLayout = new CellLayout(context, gameState.getActiveCellId());
        Digits digits =  new Dimensions().numberToDigits(gameState.getActiveCellId());

        if (cellLayout.isNoteFilled(identifier)) {
            new History().setNoteHistory(0);
            eraseNote(identifier);
        } else {
            new History().setNoteHistory(identifier);
            cellLayout.setNoteText(identifier);
            gameState.getNotes()[digits.first()][digits.second()].add(identifier);
        }
    }
}
