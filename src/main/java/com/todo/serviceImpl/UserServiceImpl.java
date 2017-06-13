package com.todo.serviceImpl;

import com.todo.entity.User;
import com.todo.forms.UserForm;
import com.todo.repository.UserRepository;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void create(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));

        this.userRepository.save(user);
    }
}
