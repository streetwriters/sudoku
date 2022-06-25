package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;

public class CellView extends VerticalLayout{
    LayoutParams params;

    public CellView(Context context){
        super(context);
        setBackgroundResource(R.drawable.unselected_button);
        setDimensions(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
    }

    void setDimensions(int width, int height, float weight){
        params = new LinearLayout
                .LayoutParams(width,height,weight);
        setParameters();
    }

    public void setMarginLeft(float left){
        Dimensions changeUnits=new Dimensions();
        params.leftMargin = changeUnits.dpToPixels(left);
        setParameters();
    }

    public void setMarginTop(float top){
        Dimensions changeUnits=new Dimensions();
        params.topMargin= changeUnits.dpToPixels(top);
        setParameters();
    }

    void setParameters(){
        setLayoutParams(params);
    }

    public void setPaddingsDP(float left, float top, float right, float bottom){
        Dimensions changeUnits=new Dimensions();
        setPadding(changeUnits.dpToPixels(left), changeUnits.dpToPixels(top), changeUnits.dpToPixels(right), changeUnits.dpToPixels(bottom));
    }
}
