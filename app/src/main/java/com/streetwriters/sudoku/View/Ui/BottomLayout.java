package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;

public class BottomLayout extends VerticalLayout{
    public BottomLayout(Context context) {
        super(context);
        setParameters(LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f);
        setPaddingsDP(5,0,5,56); //default height of action bar
        setId(R.id.bottom_layout);
        setGravity(Gravity.BOTTOM);
    }

    private void setParameters(int width,int height, float weight){
        LayoutParams params = new LinearLayout
                .LayoutParams(width,height,weight);
        setLayoutParams(params);
    }

    private void setPaddingsDP(float left, float top, float right, float bottom){
        Dimensions changeUnits=new Dimensions();
        setPadding(changeUnits.dpToPixels(left), changeUnits.dpToPixels(top), changeUnits.dpToPixels(right), changeUnits.dpToPixels(bottom));
    }

}
