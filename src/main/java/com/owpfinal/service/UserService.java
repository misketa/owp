package com.owpfinal.service;

import com.owpfinal.dto.RegistrationDto;
import com.owpfinal.model.User;

import java.util.List;

public interface UserService {

    User findOne(String email);

    User checkLogin(String email, String password);

    User registerNewUserAccount(RegistrationDto userDto);

    List<User> findAll();

    List<User> find(String email);

    void save(User user);

    void update(User user);

    User findById(Long id);
}
