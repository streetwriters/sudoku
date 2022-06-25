package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;

public class Grid extends VerticalLayout {
    LayoutParams params;

    public Grid(Context context) {
        super(context);
        setParameters();
        setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        setClickable(true);
        setFocusable(true);
    }

    public void setParameters() {
        Dimensions dimensions = new Dimensions();
        setLengthProperties(LinearLayout.LayoutParams.MATCH_PARENT,
                dimensions.getPhoneWidth());
        setLinearLayoutMarginsDP(10f, 0, 10f, 0);
        setLayoutParams(params);
    }

    void setLengthProperties(int width, int height) {
        params = new LinearLayout
                .LayoutParams(width, height);
    }

    void setLinearLayoutMarginsDP(float left, float top, float right, float bottom) {
        Dimensions dimensions=new Dimensions();
        params.setMargins(dimensions.dpToPixels(left), dimensions.dpToPixels(top), dimensions.dpToPixels(right), dimensions.dpToPixels(bottom));
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}
