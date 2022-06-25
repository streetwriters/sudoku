package com.streetwriters.sudoku.Testing;

import java.util.ArrayList;

public class ArrayToList {

    public ArrayList<Integer> getList(int[][] array){
        ArrayList<Integer> list=new ArrayList<>();

        for(int i=0;i<array.length;i++){
            for(int j=0;j<9;j++){
                list.add(array[i][j]);
            }
        }

        return list;
    }


    public ArrayList<Integer> getList(int[] array){
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<array.length;i++){
                list.add(array[i]);
        }
        return list;
    }





    public ArrayList<Integer> getListWithoutZeros(int[][] array){
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<array.length;i++){
            for(int j=0;j<9;j++){

                if(array[i][j]!=0)
                list.add(array[i][j]);
            }
        }

        return list;
    }

}
