package com.owpfinal.service;

import com.owpfinal.model.Vesti;
import com.owpfinal.model.VestiOObolelima;

import java.util.List;

public interface VestiOObolelimaService {

    List<VestiOObolelima> findAll();

    void save(VestiOObolelima vestiOObolelima);

}
