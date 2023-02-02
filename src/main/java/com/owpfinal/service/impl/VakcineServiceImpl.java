package com.owpfinal.service.impl;

import com.owpfinal.dao.VakcineDao;
import com.owpfinal.model.Vakcina;
import com.owpfinal.service.VakcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VakcineServiceImpl implements VakcineService {


    private VakcineDao vakcineDao;

    @Autowired
    public VakcineServiceImpl(@Lazy VakcineDao vakcineDao) {
        this.vakcineDao = vakcineDao;
    }

    @Override
    public List<Vakcina> findAll() {
        return vakcineDao.findAll();
    }

    @Override
    public Vakcina findOne(String naziv) {
        return vakcineDao.findOne(naziv);
    }
}
