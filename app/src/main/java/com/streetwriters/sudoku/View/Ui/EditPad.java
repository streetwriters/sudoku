package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;

public class EditPad extends HorizontalLayout{
    LinearLayout.LayoutParams params;

    public EditPad(Context context) {
        super(context);
        Dimensions dimensions = new Dimensions();
        setParameters(LinearLayout
                        .LayoutParams.MATCH_PARENT,
                dimensions.dpToPixels(30));
        setLinearLayoutMarginsDP(0,0,0,10f);

    }

    private void setParameters(int length, int width){
        params =new LinearLayout
                .LayoutParams(length,width);
        setLayoutParams(params);
    }

    void setLinearLayoutMarginsDP(float left, float top, float right, float bottom) {
        Dimensions dimensions=new Dimensions();
        params.setMargins(dimensions.dpToPixels(left), dimensions.dpToPixels(top), dimensions.dpToPixels(right), dimensions.dpToPixels(bottom));
    }

}
