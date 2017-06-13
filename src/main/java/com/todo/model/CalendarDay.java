package com.todo.model;


import org.joda.time.LocalDate;

import java.time.LocalDateTime;

public class CalendarDay {

    private LocalDate dateTime;

    private boolean busy;

    private int completed;

    private boolean proper;

    public CalendarDay() {
    }

    public CalendarDay(LocalDate dateTime, boolean busy, int completed, boolean proper) {
        this.dateTime = dateTime;
        this.busy = busy;
        this.completed = completed;
        this.proper = proper;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public boolean isProper() {
        return proper;
    }

    public void setProper(boolean proper) {
        this.proper = proper;
    }
}
