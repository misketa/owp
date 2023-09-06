package com.owpfinal.service;

import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.User;
import com.owpfinal.model.Vakcine;

import java.util.List;

public interface VakcinacijaService {

    void save(Prijavezavakcinaciju prijavaZaVakcinaciju, User user) throws Exception;

    List<Prijavezavakcinaciju> findAll();

    Prijavezavakcinaciju findOne(String string);

    void dajVakcinu(int id);

    List<Prijavezavakcinaciju> findAllByUserId(int userId);

    void deleteVakcinacija(int id);


}
