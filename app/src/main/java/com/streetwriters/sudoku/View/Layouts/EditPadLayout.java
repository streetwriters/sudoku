package com.streetwriters.sudoku.View.Layouts;

import android.app.Activity;
import android.content.Context;

import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Controller.OnClick.EditPadOnClick;
import com.streetwriters.sudoku.View.Buttons.EditPadButton;
import com.streetwriters.sudoku.View.Ui.EditPad;

public class EditPadLayout {
    Context context;
    EditPad editPad;
    int[] id = new int[]{R.id.undo, R.id.erase, R.id.take_notes, R.id.hint};
    int[] imageResource = new int[]{R.drawable.ic_undo_black_24dp, R.drawable.ic_eraser, R.drawable.ic_notes, R.drawable.ic_idea_ad};

    //R.drawable.ic_undo_black_24dp
    //R.drawable.ic_eraser
    //R.drawable.ic_notes
    //R.drawable.ic_idea_ad
    public EditPadLayout(Context context) {
        this.context =context;
        setEditPad();
    }

    void setEditPad() {
        if (((Activity)context).findViewById(R.id.edit_pad) == null) {
            editPad = new EditPad(context);
            editPad.setId(R.id.edit_pad);
        } else {
            editPad = ((Activity)context).findViewById(R.id.edit_pad);
        }
    }

    public EditPad arrange() {
        for (int i = 0; i < 4; i++) {
            EditPadButton editPadButton = new EditPadButton(context);
            editPadButton.setId(id[i]);
            editPadButton.setImageResource(imageResource[i]);
            editPadButton.setOnClickListener(new EditPadOnClick(context));
            editPad.addView(editPadButton);
        }

        return editPad;
    }

    public void setNoteTakingActiveImage() {
        ((EditPadButton) editPad.findViewById(R.id.take_notes)).setImageResource(R.drawable.ic_notes_on);
    }

    public void setNotaTakingInactiveImage() {
        ((EditPadButton) editPad.findViewById(R.id.take_notes)).setImageResource(R.drawable.ic_notes);
    }

}
