package com.owpfinal.dao;

import com.owpfinal.model.PrijavaZaVakcinaciju;
import com.owpfinal.model.Vest;

import java.util.List;

public interface VakcinacijaDaO {

    public void save(PrijavaZaVakcinaciju prijavaZaVakcinaciju);

    public List<PrijavaZaVakcinaciju> findAll();
}
