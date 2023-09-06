package com.owpfinal.dao;


import com.owpfinal.model.Vesti;
import com.owpfinal.model.VestiOObolelima;

import java.util.List;

public interface VestiOObolelimaDao {

    public List<VestiOObolelima> findAll();

    public void save(VestiOObolelima vestiOObolelima);
}
