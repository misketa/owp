package com.owpfinal.service;

import com.owpfinal.model.Vakcine;

import java.util.List;

public interface VakcineService {

    List<Vakcine> findAll();

    Vakcine findOne(String naziv);
}
