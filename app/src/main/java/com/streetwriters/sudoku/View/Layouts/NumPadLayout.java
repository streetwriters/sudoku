package com.streetwriters.sudoku.View.Layouts;

import android.app.Activity;
import android.content.Context;

import com.streetwriters.sudoku.Controller.OnClick.NumPadOnClick;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Buttons.NumPadButton;
import com.streetwriters.sudoku.View.Ui.NumPad;

public class NumPadLayout {
    Context context;
    NumPad numPad;

    public NumPadLayout(Context context) {
        this.context = context;
        setNumPad();
    }

    public void setNumPad() {
        if (((Activity) context).findViewById(R.id.numpad) == null) {
            numPad = new NumPad(context);
            numPad.setId(R.id.numpad);
        } else {
            numPad = ((Activity) context).findViewById(R.id.numpad);
        }
    }

    public NumPad arrange() {
        for (int i = 1; i <= 9; i++) {
            NumPadButton numPadButton = new NumPadButton(context);
            numPadButton.setId(new Dimensions().stringToId("num_" + i));
            numPadButton.setText(String.valueOf(i));
            numPadButton.setOnClickListener(new NumPadOnClick(context));
            numPad.addView(numPadButton);
        }

        return numPad;
    }
}
