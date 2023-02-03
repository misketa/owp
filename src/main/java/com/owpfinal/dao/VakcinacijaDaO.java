package com.owpfinal.dao;

import com.owpfinal.model.Prijavezavakcinaciju;

import java.util.List;

public interface VakcinacijaDaO {

    public void save(Prijavezavakcinaciju prijavaZaVakcinaciju);

    public List<Prijavezavakcinaciju> findAll();

    List<Prijavezavakcinaciju> findAllByUser(int userId);

}
