package com.streetwriters.sudoku.Functions;

import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

public class Selection {
    GameState gameState = GameState.getInstance();

    public int square(int BtnId) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < gameState.getBoxes()[i].size(); j++) {
                //Log.d("Selection", "square: getBoxes: "+singleton.getBoxes()[i].get(j));
                if (gameState.getBoxes()[i].get(j) == BtnId) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int row(int btnId) {
        Dimensions changeUnits = new Dimensions();
        Digits digits = changeUnits.numberToDigits(btnId);
        return digits.first();
    }

    public int column(int btnId) {
        Dimensions changeUnits = new Dimensions();
        Digits digits = changeUnits.numberToDigits(btnId);
        //Log.d("Selection", "column: digits: first: "+digits.first()+" digits: second "+ digits.second()+" btnId: "+btnId);
        return digits.second();
    }

    public Boolean isActiveCellPresent(int activeCell) {
        Boolean isActiveCellPresent = false;

        if(gameState.getHighlightedCells()[1]!=null)
            for (int index = 0; index < gameState.getHighlightedCells()[1].size(); index++) {
            if (activeCell == gameState.getHighlightedCells()[1].get(index)) {
                isActiveCellPresent = true;
            }
        }

        return isActiveCellPresent;
    }

    public Boolean isCellAbsent(int id) {
        Boolean isCellAbsent = true;

        for (int index = 0; index < gameState.getHighlightedCells()[1].size(); index++) {
            if (gameState.getHighlightedCells()[1].get(index) == id) {
                isCellAbsent = false;
            }
        }

        return isCellAbsent;
    }

    public Boolean isCellUnselected(int cellId) {
        Boolean isCellUnselected = true;
        int length0 = gameState.getHighlightedCells()[0] == null ? 0 : gameState.getHighlightedCells()[0].size();
        int length1 = gameState.getHighlightedCells()[1] == null ? 0 : gameState.getHighlightedCells()[1].size();
        int length = length0 > length1 ? length0 : length1;

        for (int index = 0; index < length; index++) {

            if (length1 > index)
                if (cellId == gameState.getHighlightedCells()[1].get(index)) {
                    isCellUnselected = false;
                }

            if (length0 > index)
                if (cellId == gameState.getHighlightedCells()[0].get(index)) {
                    if (isCellUnselected) {
                        isCellUnselected = false;
                        gameState.getHighlightedCells()[1].add(cellId);
                    }
                }
        }

        return isCellUnselected;
    }
}
