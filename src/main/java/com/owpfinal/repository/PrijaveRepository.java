package com.owpfinal.repository;

import com.owpfinal.model.Prijavezavakcinaciju;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PrijaveRepository extends JpaRepository<Prijavezavakcinaciju, Integer> {

    List<Prijavezavakcinaciju> findAllPrijavezavakcinacijusByUserId(Long id);
}
