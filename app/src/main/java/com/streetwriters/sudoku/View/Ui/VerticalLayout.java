package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

public class VerticalLayout extends LinearLayout {
    public VerticalLayout(Context context) {
        super(context);
        setVerticalOrientation();
    }

    public void setVerticalOrientation() {
        setOrientation(LinearLayout.VERTICAL);
    }

}
