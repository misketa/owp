package com.owpfinal.service;

import com.owpfinal.model.Vesti;

import java.util.List;

public interface VestiService {

    List<Vesti> findAll();

    void save(Vesti vest);
}
