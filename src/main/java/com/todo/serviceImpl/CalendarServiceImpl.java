package com.todo.serviceImpl;

import com.todo.entity.Task;
import com.todo.model.CalendarDay;
import com.todo.service.CalendarService;
import com.todo.service.TaskService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    private final TaskService taskService;

    @Autowired
    public CalendarServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public List<LocalDate> getDaysBetween(LocalDate date1, LocalDate date2) {
        List<LocalDate> days = new ArrayList<>();
        LocalDate day = date1;

        do {
            days.add(day);
            day = day.plusDays(1);
        } while (day.isBefore(date2));

        return days;
    }

    @Override
    public List<LocalDate> getDaysOfTheWeek(LocalDate date) {
        LocalDate date1 = date.withDayOfWeek(1);
        LocalDate date2 = date1.plusWeeks(1);
        return this.getDaysBetween(date1, date2);
    }

    @Override
    public List<LocalDate> getDaysOfTheMonth(LocalDate date) {
        LocalDate date1 = date.withDayOfWeek(1);
        LocalDate date2 =  date1.plusMonths(1);
        return this.getDaysBetween(date1, date2);
    }

    @Override
    public List<List<LocalDate>> getWeeksOfTheMonth(LocalDate date) {
        List<List<LocalDate>> weeks = new ArrayList<>();

        LocalDate cur = date.withDayOfMonth(1).withDayOfWeek(1);
        LocalDate end = date.withDayOfMonth(1).plusMonths(1);

        do {
            List<LocalDate> week = getDaysOfTheWeek(cur);
            //week = week.stream().filter(p -> (p.monthOfYear() == date.monthOfYear())).collect(Collectors.toList());
            weeks.add(week);

            cur = cur.plusWeeks(1);
        } while (cur.isBefore(end));

        return weeks;
    }

    @Override
    public CalendarDay makeCalendarDay(Long author, LocalDate date, int month) {
        List<Task> tasks = taskService.findTaskByAuthorAndDate(author, date);
        boolean busy = !tasks.isEmpty();
        int completed = taskService.calculateProgress(tasks);

        CalendarDay calendarDay = new CalendarDay(date, busy, completed, date.getMonthOfYear() == month);
        return calendarDay;
    }

    @Override
    public List<List<CalendarDay>> getWeeksList(Long author, LocalDate date) {
        List<List<LocalDate>> weeks = getWeeksOfTheMonth(date);
        List<List<CalendarDay>> calendarWeeks = new ArrayList<>();

        weeks.stream().forEach((week) -> {
            List<CalendarDay> calendarWeek = new ArrayList<>();

            week.stream().forEach((day) -> {
                CalendarDay calendarDay = makeCalendarDay(author, day, date.getMonthOfYear());
                calendarWeek.add(calendarDay);
            });

            calendarWeeks.add(calendarWeek);
        });

        return calendarWeeks;
    }
}
