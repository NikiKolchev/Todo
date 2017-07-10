package com.todo.controllers;

import com.todo.forms.UserForm;
import com.todo.forms.UserFormValidator;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private final UserService userService;

    private final UserFormValidator userFormValidator;

    @Autowired
    public RegisterController(UserService userService, UserFormValidator userFormValidator) {
        this.userService = userService;
        this.userFormValidator = userFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userFormValidator);
    }

    @GetMapping
    public String register(Model m){
        m.addAttribute("userForm", new UserForm());

        return "/views/register";
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public String processRegister(@Valid UserForm userForm, BindingResult result){
        if(result.hasErrors()){
            return "/views/register";
        }

        userService.create(userForm);
        return "/views/login";
    }
}
