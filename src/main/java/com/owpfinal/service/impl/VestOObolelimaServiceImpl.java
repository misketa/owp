package com.owpfinal.service.impl;

import com.owpfinal.dao.VestiOObolelimaDao;
import com.owpfinal.model.VestiOObolelima;
import com.owpfinal.service.VestiOObolelimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VestOObolelimaServiceImpl implements VestiOObolelimaService {

    @Autowired
    private VestiOObolelimaDao vestiOObolelimaDao;

    @Override
    public List<VestiOObolelima> findAll() {
        return vestiOObolelimaDao.findAll();
    }
}
