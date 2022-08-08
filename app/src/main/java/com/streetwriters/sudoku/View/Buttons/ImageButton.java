package com.streetwriters.sudoku.View.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageButton;

public class ImageButton extends AppCompatImageButton {
    public ImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageButton(Context context){
        super(context);
        setCenterTextAlignment();
        setZeroPadding();
        setSoundEffectsEnabled(false);
    }
    public void setCenterTextAlignment(){
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void setZeroPadding(){
        setPadding(0,0,0,0);
    }
}
