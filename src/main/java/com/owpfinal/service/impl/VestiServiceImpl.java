package com.owpfinal.service.impl;

import com.owpfinal.dao.VestiDao;
import com.owpfinal.model.Vesti;
import com.owpfinal.service.VestiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VestiServiceImpl implements VestiService {

    @Autowired
    private VestiDao vestiDao;

    @Override
    public List<Vesti> findAll() {
        return vestiDao.findAll();
    }

    @Override
    public void save(Vesti vest) {
        vestiDao.save(vest);
    }
}
