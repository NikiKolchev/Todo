package com.todo.controllers;

import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "/tasks")
public class TasksController {

    private final TaskService taskService;

    private final Validator validator;

    @Autowired
    public TasksController(TaskService taskService, Validator validator) {
        this.taskService = taskService;
        this.validator = validator;
    }


}
