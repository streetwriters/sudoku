package com.streetwriters.sudoku.View.Layouts;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.streetwriters.sudoku.Controller.Dialogs.GameOverDialog;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.R;

public class TextLayout {
    Activity activity;
    GameState gameState = GameState.getInstance();

    public TextLayout(Context context){
        this.activity = (Activity) context;
    }

    public void arrange(){
        setDifficulty();
        setMistakes(gameState.getMistakes());
        setTimer(gameState.getGameTimer());
    }

    public void setDifficulty(){
        TextView difficulty = activity.findViewById(R.id.difficulty);
        difficulty.setText(gameState.getDifficulty());
    }

    public String getDifficulty(){
        TextView difficulty = activity.findViewById(R.id.difficulty);
        return difficulty.getText().toString();
    }

    public void setMistakes(int num){
        if(num>-1) {
            TextView mistakes = activity.findViewById(R.id.mistakes);
            mistakes.setText(activity.getString(R.string.mistakes, num));
        }

        if(gameState.getMistakes()>2){ // this should be somewhere else but where
            Log.d(TextLayout.class.getSimpleName(), "setMistakes: "+ gameState.getMistakes());
            GameOverDialog dialog= new GameOverDialog(activity);
            dialog.Show();
        }
    }

    public void addMistake(){
        int total = gameState.getMistakes()+1;
        gameState.setMistakes(total);
        setMistakes(total);
    }

    public void subtractMistake(){
        int total = gameState.getMistakes()-1;
        gameState.setMistakes(total);
        setMistakes(total);
    }

    public void setTimer(int time){
        TextView timer = activity.findViewById(R.id.timer);
        String timePassed = String.format("%02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
        timer.setText(timePassed);
    }

}
