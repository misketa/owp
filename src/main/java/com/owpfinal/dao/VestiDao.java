package com.owpfinal.dao;

import com.owpfinal.model.User;
import com.owpfinal.model.Vest;

import java.util.List;

public interface VestiDao {

    public List<Vest> findAll();

    public void save(Vest vest);
}
