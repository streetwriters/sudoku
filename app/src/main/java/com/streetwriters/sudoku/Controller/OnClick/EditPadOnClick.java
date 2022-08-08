package com.streetwriters.sudoku.Controller.OnClick;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Functions.Objects.HistoryItem;
import com.streetwriters.sudoku.View.Layouts.CellLayout;
import com.streetwriters.sudoku.View.Layouts.EditPadLayout;
import com.streetwriters.sudoku.View.Buttons.Cell;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditPadOnClick extends ButtonOnClick implements View.OnClickListener {
    Context context;

    public EditPadOnClick(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        playSound(R.raw.keypress,context);

        if (id == R.id.undo) {
            undo();
        } else if (id == R.id.erase) {
            erase();
        } else if (id == R.id.take_notes) {
            takeNotes();
        } else if (id == R.id.hint) {
            hint();
        }
    }

    private void undo() {
        ArrayList<HistoryItem> userHistory = gameState.getUserHistory();

        if (userHistory.size() > 0) {
            //playSound(android.media.MediaPlayer.Cl,context).start();
            HistoryItem historyItem = userHistory.get(userHistory.size() - 1);
            CellLayout cellLayout = new CellLayout(context, historyItem.getCellId());

            if (historyItem.isNote()) {
                cellLayout.eraseNote(historyItem.getNoteId());
            } else {
                Cell cell = cellLayout.getCellView().findViewById(R.id.cell);
                cell.setText(historyItem.getCellText());
            }

            gameState.getUserHistory().remove(userHistory.size() - 1);
        } else {
            Toast.makeText(context, "No Actions to Undo", Toast.LENGTH_SHORT).show();
        }
    }

    private void takeNotes() {
        EditPadLayout editPad = new EditPadLayout(context);
        if (gameState.isTakingNotes()) {
            gameState.setIsTakingNotes(false);
            editPad.setNotaTakingInactiveImage();
        } else {
            gameState.setIsTakingNotes(true);
            editPad.setNoteTakingActiveImage();
        }
    }

    private void hint() {
        //InitializeAds ads = new InitializeAds(context);
        //ads.giveHint();
        if (!gameState.isTakingNotes()) {
            if (gameState.getActiveCellId() > -1) {
                if (gameState.getHints() > 0) {
                    Digits digits = new Dimensions().numberToDigits(gameState.getActiveCellId());
                    setButtonVisibility(new CellLayout(context, gameState.getActiveCellId()));
                    cellClick(gameState.getSolvedPuzzle()[digits.first()][digits.second()]);
                    if (!isRunningTest()) {
                        gameState.setHints(gameState.getHints() - 1);
                        new EditPadLayout(context).setHintIcon(gameState.getHints());
                        gameState.getRewardedAd().loadRewardedAd();
                    }
                } else {
                    gameState.getRewardedAd().displayRewardedAd();
                    gameState.setAdTypeHint(true);
                    new EditPadLayout(context).setHintIcon(gameState.getHints());
                }
            }
        } else{
            Toast.makeText(context, "Can't display hints when taking notes", Toast.LENGTH_SHORT).show();
        }
        // }
    }

    private static AtomicBoolean isRunningTest;

    public static synchronized boolean isRunningTest() {
        if (null == isRunningTest) {
            boolean istest;

            try {
                Class.forName("androidx.test.espresso.Espresso");
                istest = true;
            } catch (ClassNotFoundException e) {
                istest = false;
            }

            isRunningTest = new AtomicBoolean(istest);
        }

        return isRunningTest.get();
    }
}
