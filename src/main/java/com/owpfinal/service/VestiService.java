package com.owpfinal.service;

import com.owpfinal.model.User;
import com.owpfinal.model.Vest;

import java.util.List;

public interface VestiService {

    List<Vest> findAll();

    void save(Vest vest);
}
