package com.owpfinal.service.impl;

import com.owpfinal.dao.UserDao;
import com.owpfinal.model.User;
import com.owpfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findOne(String email) {
        return userDao.findOne(email);
    }

    @Override
    public User checkLogin(String email, String password) {
        return userDao.checkLogin(email, password);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Override
    public List<User> find(String email) { return userDao.find(email); }

    @Override
    public void save(User user) { userDao.save(user); }

    @Override
    public void update(User user) { userDao.update(user); }
}
