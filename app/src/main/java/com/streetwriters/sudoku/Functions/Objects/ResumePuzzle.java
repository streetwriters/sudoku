package com.streetwriters.sudoku.Functions.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class ResumePuzzle implements Serializable {
    int[][] riddle;
    int[][] matrix;
    int[][] solved;
    String difficulty;
    int timer;
    int mistakes;
    long startTime;
    ArrayList<Integer>[][] boardButtonNotes;
    ArrayList<HistoryItem>  userHistory;
    ArrayList<Integer>[] numberOccurencesList;


    public int[][] getRiddle() {
        return riddle;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getSolved() {
        return solved;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getTimer() {
        return timer;
    }

    public int getMistakes() {
        return mistakes;
    }

    public ArrayList<HistoryItem> getUserHistory() {
        return userHistory;
    }

    public ArrayList<Integer>[] getNumberOccurencesList() {
        return numberOccurencesList;
    }

    public void setUserHistory(ArrayList<HistoryItem> userHistory) {
        this.userHistory = userHistory;
    }

    public void setBoardButtonNotes(ArrayList<Integer>[][] boardButtonNotes) {
        this.boardButtonNotes = boardButtonNotes;
    }

    public ArrayList<Integer>[][] getBoardButtonNotes() {
        return boardButtonNotes;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setRiddle(int[][] riddle) {
        this.riddle = riddle;
    }

    public void setSolved(int[][] solved) {
        this.solved = solved;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void setNumberOccurencesList(ArrayList<Integer>[] numberOccurencesList) {
        this.numberOccurencesList = numberOccurencesList;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
