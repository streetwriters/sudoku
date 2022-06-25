package com.streetwriters.sudoku.Functions.Utils.Singletons;

import android.app.Activity;

import com.streetwriters.sudoku.Functions.InitializeArrays;
import com.streetwriters.sudoku.Functions.CellGroups;
import com.streetwriters.sudoku.Functions.FetchData.Data;
import com.streetwriters.sudoku.Functions.Objects.ResumePuzzle;
import com.streetwriters.sudoku.Functions.Utils.RandomNum;
import com.streetwriters.sudoku.R;

import test1.sudukoSolvedRiddle;

public class LoadGameState {
    Activity activity;
    GameState gameState = GameState.getInstance();

    public LoadGameState(Activity activity) {
        this.activity = activity;
    }

    public void game() {
        Data data = new Data();

        switch (getGameDifficulty()) {
            case 1:
                setGameData(data.getEasyRiddle(), activity.getString(R.string.easy));
                break;
            case 2:
                setGameData(data.getMediumRiddle(), activity.getString(R.string.medium));
                break;
            case 3:
                setGameData(data.getHardRiddle(), activity.getString(R.string.hard));
                break;
            case 4:
                setChallengeGameData();
                break;
            case 5:
                loadSavedGame();
                break;
        }
    }

    private int getGameDifficulty() {
        return activity.getIntent().getIntExtra("difficulty", 0);
    }

    public void setGameData(sudukoSolvedRiddle riddle, String difficulty) {
        gameState.setSolvedPuzzle(riddle.getMatrix());
        gameState.setUnSolvedPuzzle(riddle.getRiddle());
        gameState.setAlertDialogPresent(true);
        gameState.setDifficulty(difficulty);
        gameState.setNotes(new InitializeArrays().getNotes());
        CellGroups cellGroups = new CellGroups();
        gameState.setUserSolvedPuzzle(cellGroups.getUserSolvedPuzzle(riddle.getRiddle()));
        gameState.setColumns(cellGroups.getColumns());
        gameState.setRows(cellGroups.getRows());
        gameState.setBoxes(cellGroups.getBoxes());
        gameState.setMatchingCells(cellGroups.getMatchingCells());

    }

    public void setChallengeGameData() {
        RandomNum randomNum = new RandomNum(1, 3);
        Data data = new Data();
        int difficulty = randomNum.getRandomNum();

        if (difficulty == 1) {
            setGameData(data.getEasyRiddle(), activity.getString(R.string.easy));
        } else if (difficulty == 2) {
            setGameData(data.getMediumRiddle(), activity.getString(R.string.medium));
        } else if (difficulty == 3) {
            setGameData(data.getHardRiddle(), activity.getString(R.string.hard));
        }
    }

    public void loadSavedGame() {
        Data data = new Data();
        ResumePuzzle resumePuzzle = data.loadGameFile();

        gameState.setAlertDialogPresent(true);
        gameState.setSolvedPuzzle(resumePuzzle.getMatrix());
        gameState.setUnSolvedPuzzle(resumePuzzle.getRiddle());
        gameState.setUserSolvedPuzzle(resumePuzzle.getSolved());
        gameState.setGameTimer(resumePuzzle.getTimer());
        gameState.setMistakes(resumePuzzle.getMistakes());
        gameState.setNotes(resumePuzzle.getBoardButtonNotes());
        gameState.setIsLastScreenResume(true);
        gameState.setUserHistory(resumePuzzle.getUserHistory());
        gameState.setMatchingCells(resumePuzzle.getNumberOccurencesList());
        gameState.setDifficulty(resumePuzzle.getDifficulty());
        CellGroups cellGroups = new CellGroups();
        gameState.setColumns(cellGroups.getColumns());
        gameState.setRows(cellGroups.getRows());
        gameState.setBoxes(cellGroups.getBoxes());
        gameState.setMatchingCells(cellGroups.getMatchingCells());
    }

    public void saveGame() {
        ResumePuzzle resumePuzzle = new ResumePuzzle();
        resumePuzzle.setMatrix(gameState.getSolvedPuzzle());
        resumePuzzle.setRiddle(gameState.getUnSolvedPuzzle());
        resumePuzzle.setSolved(gameState.getUserSolvedPuzzle());
        resumePuzzle.setDifficulty(gameState.getDifficulty());
        resumePuzzle.setTimer(gameState.getGameTimer());
        resumePuzzle.setMistakes(gameState.getMistakes());
        resumePuzzle.setBoardButtonNotes(gameState.getNotes());
        resumePuzzle.setUserHistory(gameState.getUserHistory());
        resumePuzzle.setNumberOccurencesList(gameState.getMatchingCells());
        Data data = new Data();
        data.saveGameFile(resumePuzzle);
    }
}

