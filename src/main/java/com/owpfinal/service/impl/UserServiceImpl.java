package com.owpfinal.service.impl;

import com.owpfinal.dao.UserDao;
import com.owpfinal.dto.RegistrationDto;
import com.owpfinal.exception.UserAlreadyExistException;
import com.owpfinal.model.User;
import com.owpfinal.repository.UserRepository;
import com.owpfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(String email) {
        return userDao.findOne(email);
    }

    @Override
    public User checkLogin(String email, String password) {
        return userDao.checkLogin(email, password);
    }

    @Override
    public User registerNewUserAccount(RegistrationDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getEmail());
        user.setAddress(userDto.getEmail());
        user.setJmbg(userDto.getEmail());
        user.setDateOfBirth(userDto.getDateOfBirth());

        return userRepository.save(user);

    }
    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
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
