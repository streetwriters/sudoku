package com.streetwriters.sudoku.Testing;

import android.util.Log;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;

public class GameActivityTests {
    GameState gameState = GameState.getInstance();

    public void RunTests() {
        ArrayToList list = new ArrayToList();
        //Log.i("test","Unsolved Array: "+ list.getList(Global.getUnSolvedPuzzle()));
        //Log.i("test","Size: "+ list.getList(Global.getUserSolvedPuzzle()));
        for (int index = 0; index < 9; index++)
            Log.i("test", "Same Number: " + gameState.getMatchingCells()[index]);
        //Log.d("test","Array: "+ list.getListWithoutZeros(Global.getUnSolvedPuzzle()));
        //Log.d("test","Size: "+ list.getListWithoutZeros(Global.getUnSolvedPuzzle()).size());
    }
}
