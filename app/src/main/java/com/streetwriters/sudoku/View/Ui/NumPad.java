package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;

public class NumPad extends HorizontalLayout{
    LinearLayout.LayoutParams params;

    public NumPad(Context context) {
        super(context);
        setParameters(LinearLayout
                        .LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        setLinearLayoutMarginsDP(0,0,0,10f);
    }

    private void setParameters(int length,int width){
        params =new LinearLayout
                .LayoutParams(length,width);
        setLayoutParams(params);
    }

    void setLinearLayoutMarginsDP(float left, float top, float right, float bottom) {
        Dimensions dimensions=new Dimensions();
        params.setMargins(dimensions.dpToPixels(left), dimensions.dpToPixels(top), dimensions.dpToPixels(right), dimensions.dpToPixels(bottom));
    }

}
