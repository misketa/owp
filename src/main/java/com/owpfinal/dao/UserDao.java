package com.owpfinal.dao;

import com.owpfinal.model.User;

import java.util.List;

public interface UserDao {

    public User findOne(String email);

    public User checkLogin(String email, String password);

    public List<User> findAll();


    public List<User> find(String email);

    public void save(User user);

    public void update(User user);

    public User findById(int id);
}
