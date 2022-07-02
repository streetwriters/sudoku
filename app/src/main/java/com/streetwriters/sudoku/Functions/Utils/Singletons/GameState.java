package com.streetwriters.sudoku.Functions.Utils.Singletons;

import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.streetwriters.sudoku.Functions.Objects.HistoryItem;
import com.streetwriters.sudoku.Functions.Utils.Digits;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;

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
    private int hintsUsed = 0;
    private ArrayList<Integer>[] highlightedCells = new ArrayList[2];
    private ArrayList<Integer>[] boxes = new ArrayList[9];
    private ArrayList<Integer>[] columns = new ArrayList[9];
    private ArrayList<Integer>[] rows = new ArrayList[9];
    private Boolean isAlertDialogPresent;
    private ArrayList<Integer>[] matchingCells = new ArrayList[9]; //initialize
    private ArrayList<Integer> activeMatchingCells = new ArrayList<>();
    private Boolean gameOverReward =false;
    private RewardedVideoAd mRewardedVideoAd;
    private ArrayList<Integer>[][] notes = new ArrayList[9][9]; //initialize
    private Boolean isLastScreenResume= false;
    private String difficulty;
    private long startTime;

    private GameState(){
    }

    public static GameState getInstance(){
        if(singleInstance==null){
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

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void setHighlightedCells(ArrayList<Integer>[] highlightedCells) {
        this.highlightedCells = highlightedCells;
    }

    public ArrayList<Integer>[] getHighlightedCells() {
        return highlightedCells;
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

    public void setmRewardedVideoAd(RewardedVideoAd mRewardedVideoAd) {
        this.mRewardedVideoAd = mRewardedVideoAd;
    }

    public RewardedVideoAd getmRewardedVideoAd() {
        return mRewardedVideoAd;
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

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void resetSingleton(){
        singleInstance=null;
    }
}
