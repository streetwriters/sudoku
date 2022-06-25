package com.streetwriters.sudoku.Functions.Utils;

public class Digits {
    private int first;
    private int second;

    public Digits(int first,int second){
        this.first=first;
        this.second=second;
        //Log.d("Digits", "Digits: first: "+first+" second: "+second);
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }
}
