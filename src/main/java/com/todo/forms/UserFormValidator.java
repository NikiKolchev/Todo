package com.todo.forms;

import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator{

    private final UserService userService;

    @Autowired
    public UserFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

//    private void validatePassword(UserForm userForm, Errors errors) {
//        if(!userForm.getPassword().equals(userForm.getConfirmedPassword())){
//            errors.reject("passwordConform", errors, "Passwords do not match!");
//        }
//    }
}
