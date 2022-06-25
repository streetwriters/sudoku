package com.streetwriters.sudoku.View.Buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.R;

public class Cell extends Button{
    public Cell(Context context) {
        super(context);
        setTextSize(27);
        setBackgroundResource(R.drawable.unselected_button);
        setTypeFace("quicksand.ttf");
        setId(R.id.cell);
        setClickable(false);
        setText("");
        setParameters(LinearLayout.LayoutParams.MATCH_PARENT,0,1f);
    }

    void setTextSize(int TextSize){
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, TextSize);
    }

    void setTypeFace(String font ){
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), font);
        setTypeface(custom_font);
    }

    void setParameters(int width,int height,float weight){
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(width,height,weight);
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);
    }
}
