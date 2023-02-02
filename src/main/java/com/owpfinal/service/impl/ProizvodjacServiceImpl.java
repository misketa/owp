package com.owpfinal.service.impl;

import com.owpfinal.dao.ProizvodjacDao;
import com.owpfinal.dao.VakcineDao;
import com.owpfinal.model.ProizvodjacVakcine;
import com.owpfinal.service.ProizvodjacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ProizvodjacServiceImpl implements ProizvodjacService {

    private ProizvodjacDao proizvodjacDao;

    @Autowired
    public ProizvodjacServiceImpl(@Lazy ProizvodjacDao proizvodjacDao) {
        this.proizvodjacDao = proizvodjacDao;
    }

    @Override
    public ProizvodjacVakcine findOne(String proizvodjac) {
        return proizvodjacDao.findOne(proizvodjac);
    }
}
