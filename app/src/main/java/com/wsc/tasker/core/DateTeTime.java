package com.wsc.tasker.core;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTeTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public DateTeTime(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTeTime(DateTeTime date) {
        this.year = date.year;
        this.month = date.month;
        this.day = date.day;
        this.hour = date.hour;
        this.minute = date.minute;
        this.second = date.second;
    }

    @NonNull
    public static DateTeTime now() {
        LocalDateTime now = LocalDateTime.now();
        return new DateTeTime(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                now.getHour(), now.getMinute(), now.getSecond());
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public boolean isBefore(DateTeTime other) {

        return this.getYear() < other.getYear() ||
                this.getMonth() < other.getMonth() ||
                this.getDay() < other.getDay() ||
                this.getHour() < other.getHour() ||
                this.getMinute() < other.getMinute() ||
                this.getSecond() < other.getSecond();
    }
}
