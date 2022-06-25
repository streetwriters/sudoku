package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.R;

public class Note extends VerticalLayout{

    public Note(Context context){
        super(context);
        setParameters(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setVisibility(VISIBLE);
        setId(R.id.note);
    }

    void setParameters(int width,int height){
        LayoutParams params = new LinearLayout
                .LayoutParams(width,height);
        setLayoutParams(params);
    }
}
