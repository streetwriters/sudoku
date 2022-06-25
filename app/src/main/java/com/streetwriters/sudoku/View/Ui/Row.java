package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

public class Row extends HorizontalLayout{
    public Row(Context context) {
        super(context);
        setParameters(LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f);
    }

    void setParameters(int width,int height, float weight){
        LayoutParams params = new LinearLayout
                .LayoutParams(width,height,weight);
        setLayoutParams(params);
    }
}
