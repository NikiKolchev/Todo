package com.todo.service;


import com.todo.entity.Task;
import com.todo.forms.TaskForm;
import org.joda.time.LocalDate;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TaskService {

    void addTask(TaskForm taskForm);

    Task findTaskById(Long id);

    @PostFilter(value = "@taskSecurityService.isOwnerOf(principal, filterObject)")
    List<Task> findTaskByAuthorAndDate(Long author, LocalDate date);

    @PreAuthorize(value = "@taskSecurityService.isOwnerOf(principal, #id)")
    void setTaskStatus(Long id, boolean status);

    @PreAuthorize(value = "@taskSecurityService.isOwnerOf(principal, #id)")
    void pushTask(Long id);

    @PreAuthorize(value = "@taskSecurityService.isOwnerOf(principal, #id)")
    void removeTask(Long id);

    int calculateProgress(List<Task> tasks);
}
