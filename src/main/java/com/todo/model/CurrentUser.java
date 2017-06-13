package com.todo.model;


import com.todo.entity.Role;
import com.todo.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;



public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user){
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public Long getId(){
        return this.user.getId();
    }

    public Role getRole() {
        return this.user.getRole();
    }
}
