package com.todo.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;


public class TaskForm {

    private  boolean status;

    @NotEmpty
    @Size(min = 1, max = 255, message = "Task description has to be between 1-255 characters long!")
    private String todo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public TaskForm() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
