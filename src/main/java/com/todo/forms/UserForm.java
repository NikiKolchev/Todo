package com.todo.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {

    @NotNull
    private String username;

    @Size(min = 6, message = "Password has to be at least 6 characters long!")
    private String password;

    @Size(min = 6, message = "Password has to be at least 6 characters long!")
    private String confirmedPassword;

    public UserForm() {
    }

    public UserForm(String username, String password, String confirmedPassword) {
        this.username = username;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
