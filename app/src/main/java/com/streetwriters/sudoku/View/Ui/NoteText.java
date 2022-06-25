package com.streetwriters.sudoku.View.Ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.streetwriters.sudoku.Functions.Utils.Dimensions;

public class NoteText extends AppCompatTextView {
    public NoteText(Context context){
        super(context);
        setParameters(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(10);
        setTextColor(Color.WHITE);
        setPaddingsDP(0,0,0,0);
        setTypeFace("quicksand.ttf");
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

    void setTextSize(int TextSize){
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, TextSize);
    }

    void setTypeFace(String font ){
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), font);
        setTypeface(custom_font);
    }
    /*
    *     <style name="BoardNotes">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">0dp</item>
        <item name="android:layout_weight">1</item>
        <item name="android:textAlignment">center</item>
        <item name="android:textSize">10dp</item>
        <item name="android:padding">0dp</item>
    </style>
    * */
}
