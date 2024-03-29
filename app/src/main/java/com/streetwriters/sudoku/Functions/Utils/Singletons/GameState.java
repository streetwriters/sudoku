package com.streetwriters.sudoku.Functions.Utils.Singletons;

import android.util.Log;

import com.streetwriters.sudoku.Functions.Objects.HistoryItem;
import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.View.Ui.RewardedAd;

import java.util.ArrayList;

public class GameState {
    private static GameState singleInstance=null;
    private Integer activeCellId = -1;
    private int[][] solvedPuzzle;
    private int[][] unSolvedPuzzle;
    private int[][] userSolvedPuzzle;
    private ArrayList<HistoryItem> userHistory = new ArrayList<>();
    private Boolean isTakingNotes =false;
    private int gameTimer = 0;
    private int mistakes = 0;
    private int hints = 0;
    private ArrayList<Integer> previousHighlightedCells = new ArrayList<>();
    private ArrayList<Integer> currentHighlightedCells = new ArrayList<>();
    private ArrayList<Integer>[] boxes = new ArrayList[9];
    private ArrayList<Integer>[] columns = new ArrayList[9];
    private ArrayList<Integer>[] rows = new ArrayList[9];
    private Boolean isAlertDialogPresent;
    private ArrayList<Integer>[] matchingCells = new ArrayList[9]; //initialize
    private ArrayList<Integer> activeMatchingCells = new ArrayList<>();
    private Boolean gameOverReward =false;
    private ArrayList<Integer>[][] notes = new ArrayList[9][9]; //initialize
    private Boolean isLastScreenResume= false;
    private String difficulty;
    private long startTime;
    private Boolean gameFinished = false;
    private RewardedAd rewardedAd;
    private Boolean isAdTypeHint = true;
    private boolean soundEffects =true;


    private GameState(){
    }

    public static GameState getInstance(){
        if(singleInstance==null){
            Log.d("GameState", "getInstance: in null stance");
            singleInstance=new GameState();
        }
        return singleInstance;
    }

    public void setActiveCellId(Integer activeCellId) {
        this.activeCellId = activeCellId;
    }

    public Integer getActiveCellId() {
        return activeCellId;
    }

    public void setSolvedPuzzle(int[][] solvedPuzzle) {
        this.solvedPuzzle = solvedPuzzle;
    }

    public int[][] getSolvedPuzzle() {
        return solvedPuzzle;
    }

    public void setUnSolvedPuzzle(int[][] unSolvedPuzzle) {
        this.unSolvedPuzzle = unSolvedPuzzle;
    }

    public int[][] getUnSolvedPuzzle() {
        return unSolvedPuzzle;
    }

    public void updateUserSolvedPuzzle(int value) {
        Dimensions dimensions= new Dimensions();
        Digits digits=dimensions.numberToDigits(getActiveCellId());
        getUserSolvedPuzzle()[digits.first()][digits.second()]=value;
    }

    public void setUserSolvedPuzzle(int[][] userSolvedPuzzle) {
        this.userSolvedPuzzle = userSolvedPuzzle;
    }

    public int[][] getUserSolvedPuzzle() {
        return userSolvedPuzzle;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setUserHistory(ArrayList<HistoryItem> userHistory) {
        this.userHistory = userHistory;
    }

    public ArrayList<HistoryItem> getUserHistory() {
        return userHistory;
    }

    public void setIsTakingNotes(Boolean isTakingNotes) {
        this.isTakingNotes = isTakingNotes;
    }

    public Boolean isTakingNotes() {
        return isTakingNotes;
    }

    public void setGameTimer(int gameTimer) {
        this.gameTimer = gameTimer;
    }

    public int getGameTimer() {
        return gameTimer;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setHints(int hints) {
        this.hints = hints;
    }

    public int getHints() {
        return hints;
    }

    public void setPreviousHighlightedCells(ArrayList<Integer> previousHighlightedCells) {
        this.previousHighlightedCells = previousHighlightedCells;
    }

    public ArrayList<Integer> previousHighlightedCells() {
        return previousHighlightedCells;
    }

    public void setCurrentHighlightedCells(ArrayList<Integer> currentHighlightedCells) {
        this.currentHighlightedCells = currentHighlightedCells;
    }

    public ArrayList<Integer> currentHighlightedCells() {
        return currentHighlightedCells;
    }

    public void setBoxes(ArrayList<Integer>[] boxes) {
        this.boxes = boxes;
    }

    public ArrayList<Integer>[] getBoxes() {
        return boxes;
    }

    public void setColumns(ArrayList<Integer>[] columns) {
        this.columns = columns;
    }

    public ArrayList<Integer>[] getColumns() {
        return columns;
    }

    public void setRows(ArrayList<Integer>[] rows) {
        this.rows = rows;
    }

    public ArrayList<Integer>[] getRowIds() {
        return rows;
    }

    public void setAlertDialogPresent(Boolean alertDialogPresent) {
        isAlertDialogPresent = alertDialogPresent;
    }

    public Boolean getAlertDialogPresent() {
        return isAlertDialogPresent;
    }

    public void setMatchingCells(ArrayList<Integer>[] matchingCells) {
        this.matchingCells = matchingCells;
    }

    public ArrayList<Integer>[] getMatchingCells() {
        return matchingCells;
    }

    public void setActiveMatchingCells(ArrayList<Integer> activeMatchingCells) {
        this.activeMatchingCells = activeMatchingCells;
    }

    public ArrayList<Integer> getActiveMatchingCells() {
        return activeMatchingCells;
    }

    public void setGameOverReward(Boolean gameOverReward) {
        this.gameOverReward = gameOverReward;
    }

    public Boolean getGameOverReward() {
        return gameOverReward;
    }

    public void setNotes(ArrayList<Integer>[][] notes) {
        this.notes = notes;
    }

    public ArrayList<Integer>[][] getNotes() {
        return notes;
    }

    public void setIsLastScreenResume(Boolean isLastScreenResume) {
        this.isLastScreenResume = isLastScreenResume;
    }

    public Boolean getIsLastScreenResume() {
        return isLastScreenResume;
    }

    public void setGameFinished(Boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Boolean isGameFinished() {
        return gameFinished;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setRewardedAd(RewardedAd rewardedAd) {
        this.rewardedAd = rewardedAd;
    }

    public RewardedAd getRewardedAd() {
        return rewardedAd;
    }

    public void setAdTypeHint(Boolean adTypeHint) {
        isAdTypeHint = adTypeHint;
    }

    public Boolean isAdTypeHint() {
        return isAdTypeHint;
    }

    public void setSoundEffects(boolean soundEffects) {
        this.soundEffects = soundEffects;
    }

    public boolean isSoundEffects() {
        return soundEffects;
    }

    public void resetSingleton(){
        singleInstance=null;
    }
}
