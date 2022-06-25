package com.streetwriters.sudoku.Testing;

import android.util.Log;

import java.util.ArrayList;

public class TestingClass {
    private ArrayList<ArrayList<Integer>> allSudukoSquaresId = new ArrayList<>();
    private ArrayList<Integer> visibleNumbersId = new ArrayList<>();
    private int btnId;
    private int[][] riddle;
    private int SUDUKO_SIZE=9;
    ArrayList<Integer> btnPressedRow;
    ArrayList<Integer> btnPressedCol;

    public TestingClass(int[][] riddle){
        this.riddle =riddle;
        VisibleNumbersId();
    }

    public  TestingClass(){
    }

    public ArrayList<Integer> getSelectedSquare(int BtnId){
        btnId =BtnId;

        for(int size = 0; size< allSudukoSquaresId.size(); size++){
            for(int SquareSize = 0; SquareSize< allSudukoSquaresId.get(size).size(); SquareSize++){
                if(allSudukoSquaresId.get(size).get(SquareSize)==BtnId){
                    return allSudukoSquaresId.get(size);
                }
            }
        }

        return null;
    }

    public void setSecondrySudukoSqrIds(int row, int col){
        ArrayList<Integer> sudukoSquare = new ArrayList<>();

        for(int k=0;k<3;k++){
            for(int l=0; l<3; l++){
                int id = Integer.parseInt((row+l)+""+(col+k));
                sudukoSquare.add(id);
                //Log.d("id",""+sudukoCell.getSelectedButtonId());
            }
        }

        allSudukoSquaresId.add(sudukoSquare);
    }

    public ArrayList<ArrayList<Integer>> getAllSudukoSquaresId() {
        if(allSudukoSquaresId.size()==SUDUKO_SIZE) {
            return allSudukoSquaresId;
        }
        else return null;
    }


    private void VisibleNumbersId() {
        for(int i=0; i<SUDUKO_SIZE ; i++){
            for(int j=0; j<SUDUKO_SIZE; j++){
                if(riddle[i][j]!=0) {

                    visibleNumbersId.add(Integer.parseInt(i+""+j));
                    Log.d("test", "VisibleNumbersId: "+riddle[i][j]+" i:"+i+" j: "+j);
                }

            }
        }
    }


    public ArrayList<Integer> getVisibleNumbersId() {
        return visibleNumbersId;
    }

    public ArrayList<Integer> getAllSudukoIds(){
        ArrayList<Integer> all = new ArrayList<>();
        for(int i=0; i<SUDUKO_SIZE ; i++){
            for(int j=0; j<SUDUKO_SIZE; j++){
                all.add(Integer.parseInt(i+""+j));
            }
        }
        return all;
    }


    public ArrayList<Integer> getPressedBtnRow(){
        btnPressedRow = new ArrayList<>();
        String id = ""+btnId;

        if(id.length()==1){
            for(int idsH =0;idsH<SUDUKO_SIZE;idsH++){
                btnPressedRow.add(idsH);
            }
            return btnPressedRow;
        }else{
            int intFirst = Integer.parseInt(""+id.charAt(0));
            int int2nd = Integer.parseInt(""+id.charAt(1));

            for(int idsH =0;idsH<SUDUKO_SIZE; idsH++){
                int idHorizontal = Integer.parseInt(intFirst+""+idsH);
                btnPressedRow.add(idHorizontal);
            }

            return btnPressedRow;
        }
    }

    public ArrayList<Integer> getPressedBtnCol() {
        btnPressedCol = new ArrayList<>();
        String id = "" + btnId;

        if (id.length() == 1) {
            int idNum = Integer.parseInt(id);
            for(int idsV=0;idsV<SUDUKO_SIZE;idsV++){
                int idVertical = Integer.parseInt(idsV+""+idNum);
                btnPressedCol.add(idVertical);
            }

            return btnPressedCol;
        } else {
            int intFirst = Integer.parseInt("" + id.charAt(0));
            int int2nd = Integer.parseInt("" + id.charAt(1));

            for(int idsV =0; idsV <SUDUKO_SIZE;idsV++){
                int idVertical =Integer.parseInt(idsV+""+int2nd);
                btnPressedCol.add(idVertical);
            }

            return btnPressedCol;
        }
    }
}
