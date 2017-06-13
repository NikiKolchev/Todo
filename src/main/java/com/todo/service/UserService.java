package com.todo.service;


import com.todo.entity.User;
import com.todo.forms.UserForm;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    User findUserByUsername(String username);

    List<User> findAll();

    void create(UserForm userForm);
}
