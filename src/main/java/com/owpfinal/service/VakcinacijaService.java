package com.owpfinal.service;

import com.owpfinal.model.PrijavaZaVakcinaciju;
import com.owpfinal.model.Vest;

import java.util.List;

public interface VakcinacijaService {

    void save(PrijavaZaVakcinaciju prijavaZaVakcinaciju);

    List<PrijavaZaVakcinaciju> findAll();

}
