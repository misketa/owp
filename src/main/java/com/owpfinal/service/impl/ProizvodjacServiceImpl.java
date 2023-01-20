package com.owpfinal.service.impl;

import com.owpfinal.dao.ProizvodjacDao;
import com.owpfinal.model.ProizvodjacVakcine;
import com.owpfinal.service.ProizvodjacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProizvodjacServiceImpl implements ProizvodjacService {

    @Autowired
    private ProizvodjacDao proizvodjacDao;

    @Override
    public ProizvodjacVakcine findOne(String proizvodjac) {
        return proizvodjacDao.findOne(proizvodjac);
    }
}
