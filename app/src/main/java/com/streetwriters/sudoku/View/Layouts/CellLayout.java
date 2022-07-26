package com.streetwriters.sudoku.View.Layouts;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.streetwriters.sudoku.Functions.Utils.Properties;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.Functions.Utils.Dimensions;
import com.streetwriters.sudoku.View.Buttons.Cell;
import com.streetwriters.sudoku.View.Ui.CellView;
import com.streetwriters.sudoku.View.Ui.Note;
import com.streetwriters.sudoku.View.Ui.NoteRow;
import com.streetwriters.sudoku.View.Ui.NoteText;

public class CellLayout {
    private Context context;
    private CellView cellView;

    public CellLayout(Context context, int id) {
        this.context = context;
        setCellView(id);
    }

    private void setCellView(int id) {
        //Log.d("CellLayout", "setCellView: cellview: " + ((Activity) context).findViewById(id));
        if (((Activity) context).findViewById(id) == null) {
            cellView = new CellView(context);
            cellView.setId(id);
            addCell();
        } else {
            cellView = ((Activity) context).findViewById(id);

        }
    }

    public Boolean isCellValueTrue(){
        ColorStateList colors = ((Cell) cellView.findViewById(R.id.cell)).getTextColors();
        return colors.getDefaultColor() != Color.RED;
    }

    public void addCell() {
        cellView.addView(new Cell(context));
    }

    private void addNote() {
        if (!isNotePresent())
            cellView.addView(arrangeNote());
    }

    public void showCell() {
        if (!isCellVisible())
            cellView.findViewById(R.id.cell).setVisibility(View.VISIBLE);
    }

    public String getCellText() {
        //Log.d(CellLayout.class.getSimpleName(), "getCellText: " + ((Cell) cellView.findViewById(R.id.cell)).getText().toString());
        if (!((Cell) cellView.findViewById(R.id.cell)).getText().toString().equals(""))
            return ((Cell) cellView.findViewById(R.id.cell)).getText().toString();

        return "0";
    }

    public void setCellText(String text) {
        ((Cell) cellView.findViewById(R.id.cell)).setText(text);
    }

    public void setNoteText(int identifier) {
        Dimensions dimensions = new Dimensions();
        int id = dimensions.stringToId("note_" + identifier);
        if (cellView.findViewById(id) != null) {
            TextView noteText = cellView.findViewById(id);
            noteText.setText(String.valueOf(identifier));
        }
    }

    public void showNote() {
        if (!isNotePresent())
            addNote();
        if (!isNoteVisible())
            cellView.findViewById(R.id.note).setVisibility(View.VISIBLE);
    }

    public void hideCell() {
        if (isCellVisible())
            cellView.findViewById(R.id.cell).setVisibility(View.GONE);
    }

    public void hideNote() {
        Note note = cellView.findViewById(R.id.note);
        note.setVisibility(View.GONE);
    }

    public void eraseNote(int identifier) {
        Dimensions dimensions = new Dimensions();
        int id = dimensions.stringToId("note_" + identifier);
        if (cellView.findViewById(id) != null)
            ((TextView) cellView.findViewById(id)).setText("");
    }

    public void eraseCell() {
        ((Cell) cellView.findViewById(R.id.cell)).setText("");
    }

    public Boolean isNoteVisible() {
        return cellView.findViewById(R.id.note).getVisibility() == View.VISIBLE;
    }

    public Boolean isCellVisible() {
        return cellView.findViewById(R.id.cell).getVisibility() == View.VISIBLE;
    }

    public Boolean isNotePresent() {
        return cellView.findViewById(R.id.note) != null;
    }

    public Boolean isCellPresent() {
        return cellView.findViewById(R.id.cell) != null;
    }

    public Boolean isCellFilled() {
        return !((Cell) cellView.findViewById(R.id.cell)).getText().equals("");
    }

    public Boolean isNoteFilled(int identifier) {
        Dimensions dimensions = new Dimensions();
        int id = dimensions.stringToId("note_" + identifier);
        Log.d(CellLayout.class.getSimpleName(), "isNoteFilled: "+cellView.findViewById(R.id.note));
        return !((TextView) cellView.findViewById(id)).getText().equals("");
    }

    public void setActiveCellViewBackground() {
        cellView.setBackgroundResource(R.drawable.active_button);
    }

    public void setSelectedCellViewBackground() {
        cellView.setBackgroundResource(R.drawable.selected_button);
    }

    public void setActiveCellBackground() {
        ((Cell) cellView.findViewById(R.id.cell)).setBackgroundResource(R.drawable.active_button);
    }

    public void setSelectedCellBackground() {
        //Log.d("CellLayout", "setSelectedCellBackground: cell: " + cellView.findViewById(R.id.cell));
        ((Cell) cellView.findViewById(R.id.cell)).setBackgroundResource(R.drawable.selected_button);
    }

    public void setCellBackground() {
        ((Cell) cellView.findViewById(R.id.cell)).setBackgroundResource(R.drawable.unselected_button);
    }

    public void setMatchingCellBackground() {
        ((Cell) cellView.findViewById(R.id.cell)).setBackgroundResource(R.drawable.occurence_button);
    }

    public void setFixedCellBackground() {
        ((Cell) cellView.findViewById(R.id.cell)).setBackgroundResource(R.drawable.static_button);
    }

    public void setCellViewBackground() {
        cellView.setBackgroundResource(R.drawable.unselected_button);
    }

    public void setCellTextColor(int color) {
        if (cellView.findViewById(R.id.cell) != null) {
            ColorStateList colors = ((Cell) cellView.findViewById(R.id.cell)).getTextColors();
            if (colors.getDefaultColor() != Color.RED)
                ((Cell) cellView.findViewById(R.id.cell)).setTextColor(color);
        }
    }

    public void setCellErrorColor() {
        ((Cell) cellView.findViewById(R.id.cell)).setTextColor(Color.RED);
    }

    public Boolean isCellTextRed(){
        ColorStateList colors = ((Cell) cellView.findViewById(R.id.cell)).getTextColors();
        return colors.getDefaultColor() == Color.RED;
    }

    public void setNoteTypeface(int noteId, String typeface) {
        Properties properties = new Properties();
        if (cellView.findViewById(noteId) != null)
            ((TextView) cellView.findViewById(noteId)).setTypeface(properties.getTypeface("quicksand.ttf"));
    }

    public void setNoteTextColor(int noteId, int color) {
        if (cellView.findViewById(noteId) != null)
            ((TextView) cellView.findViewById(noteId)).setTextColor(color);
    }

    public void setNotesTextColor(int color) {
        for (int i = 1; i < 10; i++) {
            int id = new Dimensions().stringToId("note_" + i);
            setNoteTextColor(id, color);
        }
    }

    public Note arrangeNote() {
        int index = 1;
        Note note = new Note(context);

        for (int i = 0; i < 3; i++) {
            NoteRow noteRow = new NoteRow(context);

            for (int j = 0; j < 3; j++) {
                NoteText noteText = new NoteText(context);
                int id = new Dimensions().stringToId("note_" + index);
                noteText.setId(id);
                noteRow.addView(noteText);
                index++;
            }

            note.addView(noteRow);
        }

        return note;
    }

    public CellView getCellView() {
        return cellView;
    }

    public Note getNote() {
        return cellView.findViewById(R.id.note);
    }

    public Cell getCell() {
        return cellView.findViewById(R.id.cell);
    }

    public void setLeftPadding(){
        cellView.setPaddingsDP(0.5f,0,0,0);
    }

    public void setTopPadding(){
        cellView.setPaddingsDP(0,0.5f,0,0);
    }
}
