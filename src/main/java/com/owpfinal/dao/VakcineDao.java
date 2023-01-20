package com.owpfinal.dao;

import com.owpfinal.model.Vakcina;

import java.util.List;

public interface VakcineDao {

    public List<Vakcina> findAll();

    public Vakcina findOne(String naziv);
}
