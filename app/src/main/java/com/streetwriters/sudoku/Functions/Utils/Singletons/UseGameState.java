package com.streetwriters.sudoku.Functions.Utils.Singletons;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.View.Layouts.CellLayout;

import java.util.ArrayList;

public abstract class UseGameState {
    public GameState gameState = GameState.getInstance();

    public ArrayList<Integer> getNote(int cellId){
        Digits digits = new Dimensions().numberToDigits(cellId);
        return getNote(digits.first(), digits.second());
    }

    public ArrayList<Integer> getNote(int i,int j){
        return gameState.getNotes()[i][j];
    }

    public ArrayList<Integer> getActiveNote(){
        return getNote(gameState.getActiveCellId());
    }

    public int getNoteSize(int cellId){
        return getNote(cellId).size();
    }

    public int getActiveNoteSize(){
        return getNoteSize(gameState.getActiveCellId());
    }

    public void removeActiveNoteElement(Integer element){
        getActiveNote().remove(element);
    }

    public void addActiveNoteElement(Integer element){
        getActiveNote().add(element);
    }

    public Boolean isActiveCellClue(){
        return gameState.getActiveCellId()==-1;
    }

    public Boolean isActiveCellTrulyFilled(Context context){
        CellLayout cellLayout = new CellLayout(context,gameState.getActiveCellId());
        if(!cellLayout.isCellFilled()){
            return false;
        }else if(cellLayout.isCellTextRed()){
            return false;
        }else {
            return true;
        }
    }

    public Boolean isClue(int i,int j){
        return gameState.getUnSolvedPuzzle()[i][j] != 0;
    }

    public Boolean isCellFilled(int i,int j){
        return gameState.getUserSolvedPuzzle()[i][j] != 0;
    }

}
