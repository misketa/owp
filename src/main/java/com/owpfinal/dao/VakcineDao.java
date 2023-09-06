package com.owpfinal.dao;

import com.owpfinal.model.User;
import com.owpfinal.model.Vakcine;
import com.owpfinal.model.Vesti;

import java.util.List;

public interface VakcineDao {

    public List<Vakcine> findAll();

    public Vakcine findOne(int vakcina_id);

    public void update(Vakcine vakcina);

    public void save(Vakcine vakcina);
}
