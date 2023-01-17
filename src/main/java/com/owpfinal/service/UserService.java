package com.owpfinal.service;

import com.owpfinal.model.User;

import java.util.List;

public interface UserService {

    User findOne(String email);

    User checkLogin(String email, String password);

    List<User> findAll();

    List<User> find(String email);

    void save(User user);

    void update(User user);
}
