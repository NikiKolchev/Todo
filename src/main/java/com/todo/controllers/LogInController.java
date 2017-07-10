package com.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LogInController {

    @GetMapping
    public String login(@RequestParam(required = false) String error, Model model){

        if(error != null){
            model.addAttribute("error", error);
        }

        return "/views/login";
    }
}
