package com.streetwriters.sudoku.Functions.Utils;

import java.util.Random;

public class RandomNum{
    int randomNum;

    public RandomNum(int min, int max){
        this.randomNum= randInt(min,max);
    }

    public int getRandomNum() {
        return randomNum;
    }

    static int randInt(int min, int max) {
        Random rand=new Random(System.currentTimeMillis());
        return rand.nextInt((max - min) + 1) + min;
    }
}
