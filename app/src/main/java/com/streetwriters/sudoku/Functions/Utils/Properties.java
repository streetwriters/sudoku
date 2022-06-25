package com.streetwriters.sudoku.Functions.Utils;

import android.graphics.Typeface;

public class Properties {

    public Typeface getTypeface(String font){
        return Typeface.createFromAsset(MyApplication.getContext().getAssets(), font);
    }
}
