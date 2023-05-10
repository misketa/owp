package com.owpfinal.dao.impl;

import com.owpfinal.dao.UserDao;
import com.owpfinal.enumeration.Role;
import com.owpfinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            Integer id = rs.getInt(index++);
            String email = rs.getString(index++);
            String password = rs.getString(index++);
            String name = rs.getString(index++);
            String lastName = rs.getString(index++);
            Date dateOfBirth = rs.getDate(index++);
            String jmbg = rs.getString(index++);
            String address = rs.getString(index++);
            String phone = rs.getString(index++);
            String dateOfRegistration = rs.getString(index++);
            String role = rs.getString(index++);

            User user = new User();

            user.setId(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setLastName(lastName);
            user.setDateOfBirth(dateOfBirth);
            user.setJmbg(jmbg);
            user.setAddress(address);
            user.setPhone(phone);
            user.setDateOfRegistration(dateOfRegistration);
            user.setRole(Role.valueOf(role).name());


            return user;
        }
    }


    @Override
    public User findOne(String email) {
        try {
            String sql = "SELECT  id, email, password, name, last_name, date_of_birth, jmbg, address, phone, " +
                    "date_of_registration, role FROM users WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User checkLogin(String email, String password) {
        try {
            String sql = "SELECT id, email, password, name, last_name, date_of_birth, jmbg, address, phone," +
                    "date_of_registration, role FROM users WHERE email = ? AND password = ?";
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email, password);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }


    @Override
    public List<User> find(String email) {

        ArrayList<Object> argumentList = new ArrayList<Object>();

        String sql = "SELECT * FROM users";

        StringBuffer whereSql = new StringBuffer(" WHERE ");

        if (email != null) {
            email = "%" + email + "%";
            whereSql.append(" AND ");
            whereSql.append("email LIKE ?");
            argumentList.add(email);
        }

        return jdbcTemplate.query(sql, argumentList.toArray(), new UserRowMapper());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (email, password, name, lastName, dateOfBirth, jmbg, address, phone," +
                " dateOfRegistration, role) " +
                "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(),
                user.getLastName(), user.getDateOfBirth(), user.getJmbg(), user.getAddress(), user.getPhone(), user.getDateOfRegistration(),
                user.getRole().toString());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET email = ?, password = ?, name = ?, last_name = ?, date_of_birth = ?, address = ?, phone = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), user.getLastName(),
                user.getDateOfBirth(), user.getAddress(), user.getPhone(), user.getId());
    }

    @Override
    public User findById(Long id) {
        try {
            String sql = "SELECT  id, email, password, name, last_name, date_of_birth, jmbg, address, phone, " +
                    "date_of_registration, role FROM users WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
