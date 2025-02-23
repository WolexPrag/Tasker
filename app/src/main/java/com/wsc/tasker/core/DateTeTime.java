package com.wsc.tasker.core;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTeTime {
    private final LocalDateTime _dateTime;

    public DateTeTime() {
        _dateTime = LocalDateTime.now();
    }

    public DateTeTime(LocalDateTime dateTime) {
        _dateTime = dateTime;
    }

    public DateTeTime(DateTeTime value) {
        _dateTime = LocalDateTime.from(value._dateTime);
    }

    public DateTeTime copy() {
        return new DateTeTime(this);
    }

    public String format(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return this._dateTime.format(formatter);
    }

    public String toString() {
        return _dateTime.toString();
    }

    public Boolean equals(@NonNull DateTeTime other) {
        return other._dateTime.equals(this._dateTime);
    }
    public Boolean isAfter(@NonNull DateTeTime other){
        return _dateTime.isAfter(other._dateTime);
    }
    public Boolean isBefore(@NonNull DateTeTime other){
        return _dateTime.isBefore(other._dateTime);
    }

    public Boolean isInArea(DateTeTime min, DateTeTime max) {
        return (isAfter(min) || equals(min)) &&
                (isBefore(max) || equals(max._dateTime));
    }
    public Boolean isTheSameDay(DateTeTime other){
        return this._dateTime.getDayOfYear() == other._dateTime.getDayOfYear() && this._dateTime.getYear() == other._dateTime.getYear();
    }

    public static DateTeTime getCurrentDate() {
        return new DateTeTime(LocalDateTime.now());
    }
}
