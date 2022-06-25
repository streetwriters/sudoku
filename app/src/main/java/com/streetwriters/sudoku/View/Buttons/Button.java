package com.streetwriters.sudoku.View.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class Button extends AppCompatButton {
    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Button(Context context){
        super(context);
        setCenterTextAlignment();
        setZeroPadding();
    }

    void setCenterTextAlignment(){
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    void setZeroPadding(){
        setPadding(0,0,0,0);
    }
}
