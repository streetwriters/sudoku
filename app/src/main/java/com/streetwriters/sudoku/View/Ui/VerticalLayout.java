package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class VerticalLayout extends LinearLayout {
    public VerticalLayout(Context context) {
        super(context);
        setVerticalOrientation();
    }

    public VerticalLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VerticalLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setVerticalOrientation() {
        setOrientation(LinearLayout.VERTICAL);
    }

}
