package com.owpfinal.service.impl;

import com.owpfinal.dao.VakcinacijaDaO;
import com.owpfinal.model.PrijavaZaVakcinaciju;
import com.owpfinal.service.VakcinacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VakcinacijaServiceImpl implements VakcinacijaService {

    @Autowired
    private VakcinacijaDaO vakcinacijaDaO;

    @Override
    public void save(PrijavaZaVakcinaciju prijavaZaVakcinaciju) {
            vakcinacijaDaO.save(prijavaZaVakcinaciju);
    }

    @Override
    public List<PrijavaZaVakcinaciju> findAll() {
        return vakcinacijaDaO.findAll();
    }
}
