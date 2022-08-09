package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;

public class VerticalBorder extends VerticalLayout{
    public VerticalBorder(Context context) {
        super(context);
        setBackgroundColor(getResources().getColor(R.color.gray01));
        setParameters(0, LinearLayout.LayoutParams.MATCH_PARENT,0.025f);
    }

    void setParameters(float width,int height, float weight){
        Dimensions changeUnits=new Dimensions();
        LayoutParams params = new LinearLayout
                .LayoutParams(changeUnits.dpToPixels(width),height,weight);
        setLayoutParams(params);
    }
}
