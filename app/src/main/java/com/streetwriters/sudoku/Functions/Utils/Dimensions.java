package com.streetwriters.sudoku.Functions.Utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.streetwriters.sudoku.R;

public class Dimensions {

    public int dpToPixels(float yourdpmeasure){
        Resources r = MyApplication.getContext().getResources();

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                yourdpmeasure,
                r.getDisplayMetrics()
        );

        return px;
    }

    public int getPhoneWidth(){
        DisplayMetrics displayMetrics = com.streetwriters.sudoku.Functions.Utils.MyApplication.getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels;
        return (int)dpWidth;
    }

    public int getActionBarHeight() {
        int[] attrs = new int[] {R.attr.actionBarSize};
        TypedArray ta = com.streetwriters.sudoku.Functions.Utils.MyApplication.getContext().obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }

    public int stringToId(String stringId){
        Resources r = com.streetwriters.sudoku.Functions.Utils.MyApplication.getContext().getResources();
        String name= com.streetwriters.sudoku.Functions.Utils.MyApplication.getContext().getPackageName();
        int id = r.getIdentifier(stringId, "id", name);
        return id;
    }

    public String monthToString(int month) {
        if (month == 1) {
            return "January";
        } else if (month == 2) {
            return "February";
        } else if (month == 3) {
            return "March";
        } else if (month == 4) {
            return "April";
        } else if (month == 5) {
            return "May";
        } else if (month == 6) {
            return "June";
        } else if (month == 7) {
            return "July";
        } else if (month == 8) {
            return "August";
        } else if (month == 9) {
            return "September";
        } else if (month == 10) {
            return "October";
        } else if (month == 11) {
            return "November";
        } else if (month == 12) {
            return "December";
        } else {
            return "";
        }
    }

    public Digits numberToDigits(int num) {
        int firstDigit;
        int secondDigit;
        String number = String.valueOf(num);
        char[] digits = number.toCharArray();

        if(digits.length>1){
            firstDigit=Integer.parseInt(String.valueOf(digits[0]));
            secondDigit = Integer.parseInt(String.valueOf(digits[1]));
        }else{
            firstDigit=0;
            secondDigit=Integer.parseInt(String.valueOf(digits[0]));
        }

        //Log.d("Dimensions", "numberToDigits: string "+number+" digits: "+digits[0]+" firstDigit: "+firstDigit+" secondDigit: "+secondDigit);
        return new Digits(firstDigit,secondDigit);
    }
}
