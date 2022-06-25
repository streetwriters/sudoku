package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;

public class NoteRow extends HorizontalLayout{
    public NoteRow(Context context){
        super(context);
        setParameters(LinearLayout.LayoutParams.MATCH_PARENT, 0,1f);
        setVisibility(VISIBLE);
        setId(R.id.note);
        setPaddingsDP(0,0,0,0);
    }

    void setParameters(int width,int height,float weight){
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(width,height,weight);
        setLayoutParams(params);
    }

    public void setPaddingsDP(float left, float top, float right, float bottom){
        Dimensions changeUnits=new Dimensions();
        setPadding(changeUnits.dpToPixels(left), changeUnits.dpToPixels(top), changeUnits.dpToPixels(right), changeUnits.dpToPixels(bottom));
    }
}
