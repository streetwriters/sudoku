package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.R;

public class HorizontalBorder extends HorizontalLayout{
    public HorizontalBorder(Context context) {
        super(context);
        setBackgroundColor(getResources().getColor(R.color.gray01));
        setParameters(LayoutParams.MATCH_PARENT,
                0, 0.025f);
    }

    void setParameters(int width,int height, float weight){
        LayoutParams params = new LinearLayout
                .LayoutParams(width,height,weight);
        setLayoutParams(params);
    }
}
