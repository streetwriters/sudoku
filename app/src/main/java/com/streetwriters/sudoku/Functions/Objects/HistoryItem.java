package com.streetwriters.sudoku.Functions.Objects;

import java.io.Serializable;

public class HistoryItem implements Serializable {
    private int cellId;
    private int noteId;
    private String cellText;
    private String noteText;
    boolean isNote =false;

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public void setCellText(String btn_num) {
        cellText = btn_num;
    }

    public int getCellId() {
        return cellId;
    }

    public String getCellText() {
        if(cellText!="0")
        return cellText;
        else
          return "";
    }

    public void setIsNote(boolean note) {
        isNote = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public boolean isNote() {
        return isNote;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteText() {
        return noteText;
    }
}
