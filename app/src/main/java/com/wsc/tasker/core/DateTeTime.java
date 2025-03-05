package com.wsc.tasker.core;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class DateTeTime {
    private LocalDateTime dateTime;

    private DateTeTime() {
        throw new UnsupportedOperationException("Please use the now() method to get an instance.");
    }

    private DateTeTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getYear() {
        return dateTime.getYear();
    }

    public void setYear(int year) {
        this.dateTime = this.dateTime.withYear(year);
    }

    public int getMonth() {
        return dateTime.getMonthValue();
    }

    public void setMonth(int month) {
        this.dateTime = this.dateTime.withMonth(month);
    }

    public int getDay() {
        return dateTime.getDayOfMonth();
    }

    public void setDay(int day) {
        this.dateTime = this.dateTime.withDayOfMonth(day);
    }

    public int getHour() {
        return dateTime.getHour();
    }

    public void setHour(int hour) {
        this.dateTime = this.dateTime.withHour(hour);
    }

    public int getMinute() {
        return dateTime.getMinute();
    }

    public void setMinute(int minute) {
        this.dateTime = this.dateTime.withMinute(minute);
    }

    public int getSecond() {
        return dateTime.getSecond();
    }

    public void setSecond(int second) {
        this.dateTime = this.dateTime.withSecond(second);
    }

    public boolean isBeforeDateOnly(DateTeTime other) {
        return this.dateTime.toLocalDate().isBefore(other.dateTime.toLocalDate());
    }

    public boolean isAfterDateOnly(DateTeTime other) {
        return this.dateTime.toLocalDate().isAfter(other.dateTime.toLocalDate());
    }

    public boolean isEqualDateOnly(DateTeTime other) {
        return this.dateTime.toLocalDate().isEqual(other.dateTime.toLocalDate());
    }

    public boolean isBefore(DateTeTime other) {
        return this.dateTime.isBefore(other.dateTime);
    }

    public boolean isEqual(DateTeTime other) {
        return this.dateTime.equals(other.dateTime);
    }

    public boolean isAfter(DateTeTime other) {
        return this.dateTime.isAfter(other.dateTime);
    }

    @Override
    @NonNull
    public String toString() {
        return dateTime.toString();
    }

    public static DateTeTime now() {
        return new DateTeTime(LocalDateTime.now());
    }
}
