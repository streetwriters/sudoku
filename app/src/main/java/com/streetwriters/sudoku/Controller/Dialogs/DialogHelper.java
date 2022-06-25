package com.streetwriters.sudoku.Controller.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public abstract class DialogHelper {
    AlertDialog.Builder builder;
    Activity activity;
    View v;
    ArrayList<Button> buttonList= new ArrayList<>();

    public DialogHelper(Activity activity){
        builder = new AlertDialog.Builder(activity);
        this.activity=activity;
    }

    public void setLayout(int layout){
        LayoutInflater inflater = LayoutInflater.from(activity);
        v= inflater.inflate(layout,null);
    }

    public void setButton(int id,View.OnClickListener listener){
        Button button = v.findViewById(id);
        button.setOnClickListener(listener);
        buttonList.add(button);
    }


    public void setCustomView(){
        builder.setView(v);
    }

    public View getCustomView(){
        return v;
    }

    public void setQuitTitle(){
        builder.setTitle("Quit This Game");
    }

    public void setQuitMessage(){
        builder.setMessage("Are you sure you want to quit this game? \nAll your progress will be lost.");
    }

    public void setCancel(Boolean bool){
        builder.setCancelable(bool);
    }

    public void setQuitPositiveButton(DialogInterface.OnClickListener dialogOnClickListner){
        builder.setPositiveButton("Quit", dialogOnClickListner);
    }

    public void setQuitNegativeButton(DialogInterface.OnClickListener dialogOnClickListner){
        builder.setNegativeButton("No",dialogOnClickListner);
    }

    public AlertDialog getDialog(){
        return builder.create();
    }

}
