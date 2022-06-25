package com.streetwriters.sudoku.Functions.Objects;

import java.io.Serializable;

public class DailyChallengeClass implements Serializable {
    int year;
    int month;
    int day;

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
