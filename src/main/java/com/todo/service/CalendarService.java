package com.todo.service;


import com.todo.model.CalendarDay;

import org.joda.time.LocalDate;
import java.util.List;

public interface CalendarService {

    List<LocalDate> getDaysBetween(LocalDate date1, LocalDate date2);

    List<LocalDate> getDaysOfTheWeek(LocalDate date);

    List<LocalDate> getDaysOfTheMonth(LocalDate date);

    List<List<LocalDate>> getWeeksOfTheMonth(LocalDate date);

    CalendarDay makeCalendarDay(Long author, LocalDate date,  int month);

    List<List<CalendarDay>> getWeeksList(Long author, LocalDate date);
}
