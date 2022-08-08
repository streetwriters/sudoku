package com.streetwriters.sudoku.Functions.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;

import androidx.annotation.ColorInt;

import com.streetwriters.sudoku.R;

public class Properties {

    public Typeface getTypeface(String font){
        return Typeface.createFromAsset(MyApplication.getContext().getAssets(), font);
    }

    public @ColorInt int getThemeColor(int attr, Context context){
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        @ColorInt int color = typedValue.data;
        return color;
    }
}
