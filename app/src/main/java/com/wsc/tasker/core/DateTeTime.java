package com.wsc.tasker.core;

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

    public Boolean equals(DateTeTime value) {
        return value._dateTime.equals(this._dateTime);
    }

    public static DateTeTime GetCurrentDate() {
        return new DateTeTime(LocalDateTime.now());
    }
}
