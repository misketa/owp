package com.owpfinal.service;

import com.owpfinal.model.Vakcine;
import com.owpfinal.model.Vesti;

import java.util.List;

public interface VakcineService {

    List<Vakcine> findAll();

    Vakcine findOne(int vakcina_id);

    void update(Vakcine vakcina);

    void save(Vakcine vakcina);

}
