package com.owpfinal.dao;

import com.owpfinal.model.Vakcine;

import java.util.List;

public interface VakcineDao {

    public List<Vakcine> findAll();

    public Vakcine findOne(String naziv);
}
