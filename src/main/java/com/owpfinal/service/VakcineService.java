package com.owpfinal.service;

import com.owpfinal.model.Vakcina;

import java.util.List;

public interface VakcineService {

    List<Vakcina> findAll();

    Vakcina findOne(String naziv);
}
