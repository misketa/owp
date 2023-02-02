package com.owpfinal.repository;

import com.owpfinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Object findByEmail(String email);
}
