package com.streetwriters.sudoku.Functions;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class Position {
    private int x;
    private int y;

    public Position(View view, MotionEvent event,int gridWidth,int gridLength){
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int x1 = rect.left;
        int x2 = rect.right;
        int y1 = rect.top;
        int y2 = rect.bottom;
        int recWidth = x2 - x1;
        int recHeight = y2 - y1;
        int unitWidth = recWidth / gridWidth;
        int unitHeight = recHeight / gridLength;
        this.x = (rawX - x1) / unitWidth;
        this.y = (rawY - y1) / unitHeight;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public Boolean isUnchanged(int previousX,int previousY){
        Boolean isXUnchanged = (x == previousX);
        Boolean isYUnchanged = (y == previousY);
        Boolean isUnchanged = isXUnchanged && isYUnchanged;
        return isUnchanged;
    }

    public Boolean isWithinLimits(){
        Boolean isYWithinMaxLimit = (y < 9);
        Boolean isYWithinMinLimit = (y > -1);
        Boolean isYWithinLimit = isYWithinMaxLimit && isYWithinMinLimit;
        Boolean isXWithinMaxLimit = (x < 9);
        Boolean isXWithinMinLimit = (x > -1);
        Boolean isXWithinLimit = isXWithinMaxLimit && isXWithinMinLimit;
        Boolean isWithinLimits= isYWithinLimit && isXWithinLimit;
        return isWithinLimits;
    }
}
