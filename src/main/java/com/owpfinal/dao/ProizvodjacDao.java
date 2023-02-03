package com.owpfinal.dao;

import com.owpfinal.model.Proizvodjaci;

public interface ProizvodjacDao {

    public Proizvodjaci findOne(String proizvodjac);

    void smanjiKolicinu(int id);

    Proizvodjaci findOne(int id);
}
