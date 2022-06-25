package com.streetwriters.sudoku.Functions.FetchData;

import android.util.Log;

import com.streetwriters.sudoku.Functions.Objects.Games;
import com.streetwriters.sudoku.Functions.Objects.ResumePuzzle;
import com.streetwriters.sudoku.Functions.Utils.RandomNum;

import java.util.ArrayList;

import test1.sudukoSolvedRiddle;

public class Data extends Files {
    private final String RESUME_FILE = "resume.dat";
    private final String EASY_FILE="EasyRiddles.dat";
    private final String MEDIUM_FILE="MediumRiddles.dat";
    private final String HARD_FILE="DifficultRiddles.dat";
    private final String GAME_STATS="main.dat";

    public sudukoSolvedRiddle getEasyRiddle() {
        return getRiddle(EASY_FILE);
    }

    public sudukoSolvedRiddle getMediumRiddle() {
        return getRiddle(MEDIUM_FILE);
    }

    public sudukoSolvedRiddle getHardRiddle() {
        return getRiddle(HARD_FILE);
    }

    public ResumePuzzle loadGameFile(){
        ResumePuzzle resumePuzzle=new ResumePuzzle();

        try {
            resumePuzzle = (ResumePuzzle)load(RESUME_FILE);
        }catch (Exception e){
            Log.d("ASSETS", "getResumePuzzle: "+e.getMessage());
        }

        return resumePuzzle;
    }

    public void saveGameFile(ResumePuzzle resumePuzzle){
        try {
            save(resumePuzzle,RESUME_FILE);
        }catch (Exception e){
            Log.d("ASSETS", "setResumePuzzle: "+e.getMessage());
        }
    }

    public void deleteGameFile(){
        try {
            delete(RESUME_FILE);
        }catch (Exception e){
            Log.d("Data", "setResumePuzzle: "+e.getMessage());
        }
    }

    public ArrayList<Games> getGameStats(){
        ArrayList<Games> GamesList = new ArrayList<>();

        try {
            GamesList = (ArrayList<Games>)load(GAME_STATS);
        }catch (Exception e){
            Log.d("ASSETS", "getGameStats: "+e.getMessage());
        }

        return GamesList;
    }

    private sudukoSolvedRiddle getRiddle(String filename){
        ArrayList<sudukoSolvedRiddle> puzzle=new ArrayList<>();

        try {
            puzzle= (ArrayList<sudukoSolvedRiddle>)loadAssets(filename);
        }catch (Exception e){
            Log.d("ASSETS", "getPuzzles: "+e.getMessage());
        }

        return puzzle.get(getRandomNum(1000));
    }

    private int getRandomNum(int totalRiddles){
        RandomNum randomNum=new RandomNum(0,totalRiddles-1);
        return randomNum.getRandomNum();
    }
}
