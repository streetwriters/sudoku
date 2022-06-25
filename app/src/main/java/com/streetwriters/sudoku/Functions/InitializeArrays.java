package com.streetwriters.sudoku.Functions;

import java.util.ArrayList;

public class InitializeArrays {
    public ArrayList<Integer>[] getMatchingCells(){
        ArrayList<Integer>[] matchingCells= new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            matchingCells[i] = new ArrayList<Integer>();
        }
        return matchingCells;
    }

    public ArrayList<Integer>[][] getNotes(){
        ArrayList<Integer>[][] notes = new ArrayList[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                notes[i][j] = new ArrayList<>();
            }
        }
        return notes;
    }
}
