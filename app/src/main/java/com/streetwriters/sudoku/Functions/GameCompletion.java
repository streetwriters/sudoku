package com.streetwriters.sudoku.Functions;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

import java.util.Arrays;

public class GameCompletion {
    GameState gameState = GameState.getInstance();
    int[][] solvedPuzzle= gameState.getSolvedPuzzle();
    int[][] userSolvedPuzzle= gameState.getUserSolvedPuzzle();

    public Boolean isGameWon(){
        return Arrays.deepEquals(userSolvedPuzzle,solvedPuzzle);
    }
}
