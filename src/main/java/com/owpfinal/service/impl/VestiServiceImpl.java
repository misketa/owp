package com.owpfinal.service.impl;

import com.owpfinal.dao.VestiDao;
import com.owpfinal.model.Vest;
import com.owpfinal.service.VestiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VestiServiceImpl implements VestiService {

    @Autowired
    private VestiDao vestiDao;

    @Override
    public List<Vest> findAll() {
        return vestiDao.findAll();
    }

    @Override
    public void save(Vest vest) {
        vestiDao.save(vest);
    }
}
