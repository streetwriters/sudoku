package com.streetwriters.sudoku.View.Buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Properties;
import com.streetwriters.sudoku.R;

public class NumPadButton extends Button{
    public NumPadButton(Context context) {
        super(context);
        setParameters();
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        setTextColor(new Properties().getThemeColor(R.attr.colorAccent,context));
        setBackground(getResources().getDrawable(R.drawable.editing_button_bakcground));
        setTypeFace("quicksand.ttf");
    }

    void setParameters() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
        setLayoutParams(params);
    }

    void setTypeFace(String font ){
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), font);
        setTypeface(custom_font);
    }
}
