package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

public class HorizontalLayout extends android.widget.LinearLayout {
    public HorizontalLayout(Context context) {
        super(context);
        setHorizontalOrientation();
    }

    void setHorizontalOrientation() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

}
