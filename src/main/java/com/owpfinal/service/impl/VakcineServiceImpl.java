package com.owpfinal.service.impl;

import com.owpfinal.dao.VakcineDao;
import com.owpfinal.model.Vakcine;
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
    public List<Vakcine> findAll() {
        return vakcineDao.findAll();
    }

    @Override
    public Vakcine findOne(int vakcina_id) {
        return vakcineDao.findOne(vakcina_id);
    }

    @Override
    public void update(Vakcine vakcina) {
        vakcineDao.update(vakcina);
    }

    @Override
    public void save(Vakcine vakcina) {
        vakcineDao.save(vakcina);
    }
}
