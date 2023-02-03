package com.owpfinal.dao;

import com.owpfinal.model.Vesti;

import java.util.List;

public interface VestiDao {

    public List<Vesti> findAll();

    public void save(Vesti vest);
}
