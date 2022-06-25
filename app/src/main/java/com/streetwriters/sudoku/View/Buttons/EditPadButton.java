package com.streetwriters.sudoku.View.Buttons;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.streetwriters.sudoku.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class EditPadButton extends ImageButton{
    public EditPadButton(Context context) {
        super(context);
        setBackground();
        setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        setPadding(0);
        setParameters();
    }

    void setBackground(){
        super.setBackground(getResources().getDrawable(R.drawable.editing_button_bakcground));
    }

    public void setPadding(int unit){
        setPadding(unit,unit,unit,unit);
    }

   void setParameters() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f);
        setLayoutParams(params);
    }
}
