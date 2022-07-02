package com.streetwriters.sudoku.Functions.Objects;

import java.io.Serializable;

/*
        games.dat will have more data in one class Object
        The data will be as follows:
        game difficulty (1,2,3) for easy, medium and hard
        no of mistakes made
        time taken
        game completed no
        score made
        game lost or won
 */

public class Game implements Serializable {
    private int difficulty;
    private int mistakes;
    private int time;
    private long startDate;
    private int gameNo;
    private double score; //5 for each good move
    private int result;
    private DailyChallengeClass dailyChallengeClass;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDailyChallengeClass(DailyChallengeClass dailyChallengeClass) {
        this.dailyChallengeClass = dailyChallengeClass;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getGameNo() {
        return gameNo;
    }

    public int getMistakes() {
        return mistakes;
    }

    public int getResult() {
        return result;
    }

    public double getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    public DailyChallengeClass getDailyChallengeClass() {
        return dailyChallengeClass;
    }

    public void setStartTime(long startDate) {
        this.startDate = startDate;
    }

    public long getStartDate() {
        return startDate;
    }
}
