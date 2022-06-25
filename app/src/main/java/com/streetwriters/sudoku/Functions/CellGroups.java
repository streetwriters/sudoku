package com.streetwriters.sudoku.Functions;

import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;

import java.util.ArrayList;

public class CellGroups extends UseGameState {
    GameState gameState = GameState.getInstance();

    public ArrayList<Integer>[] getBoxes(){
        int[] row = new int[]{0, 3, 6};
        int[] col = new int[]{0, 3, 6};
        ArrayList<Integer>[] squares = new ArrayList[9];
        int squareIndex=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Integer> square= new ArrayList<>();
                for (int k = 0; k <3 ; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(!isClue(row[i]+l,col[j]+k)) {
                            square.add(Integer.parseInt((row[i] + l) + "" + (col[j] + k)));
                        }
                    }
                }
                squares[squareIndex]=square;
                squareIndex++;
            }
        }

        return squares;
    }

    public ArrayList<Integer>[] getColumns(){
        ArrayList<Integer>[] columns= new ArrayList[9];

        for(int i=0;i<9;i++){
            ArrayList<Integer> column= new ArrayList<>();
            for(int j=0;j<9;j++){
                if (!isClue(i,j)) {
                    column.add(Integer.parseInt(j + "" + i));
                }//if state
            }//inner for loop
            columns[i]=column;
        }//outer for loop

        return columns;
    }//end method

    public ArrayList<Integer>[] getRows(){
        ArrayList<Integer>[] rows= new ArrayList[9];

        for(int i=0;i<9;i++){
            ArrayList<Integer> row= new ArrayList<>();
            for(int j=0;j<9;j++){
                if (!isClue(i,j)) {
                    row.add(Integer.parseInt(i + "" + j));
                }//if state
            }//inner for loop
            rows[i]=row;
        }//outer for loop

        return rows;
    }//end method

    public ArrayList<Integer>[] getMatchingCells() {
        ArrayList<Integer>[] matchingCells= new InitializeArrays().getMatchingCells();
        //new InitializeArrays().
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int cellValue= gameState.getUserSolvedPuzzle()[i][j];
                if (cellValue != 0) {
                    int index=cellValue-1;
                    matchingCells[index].add(Integer.parseInt(i + "" + j));
                }
            }
        }

        return matchingCells;
    }

    public void addMatchingCell(int number, int id){
        gameState.getMatchingCells()[number-1].add(id);
    }

    public void removeMatchingCell(int number, Integer id){
        gameState.getMatchingCells()[number-1].remove(id);
    }

    public void updateActiveMatchingCells(int buttonValue){
        ArrayList<Integer> matchingCell = gameState.getMatchingCells()[buttonValue - 1];
        gameState.setActiveMatchingCells(matchingCell);
    }

    public int[][] getUserSolvedPuzzle(int[][] riddle){
        int[][] useSolvedPuzzle= new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                useSolvedPuzzle[i][j] = riddle[i][j];
            }
        }

        return useSolvedPuzzle;
    }

}
