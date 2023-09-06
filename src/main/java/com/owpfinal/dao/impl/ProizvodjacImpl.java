package com.owpfinal.dao.impl;

import com.owpfinal.dao.ProizvodjacDao;
import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.Proizvodjaci;
import com.owpfinal.model.Vakcine;
import com.owpfinal.service.VakcinacijaService;
import com.owpfinal.service.VakcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProizvodjacImpl implements ProizvodjacDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private class ProizvodjacRowMapper implements RowMapper<Proizvodjaci> {

        @Override
        public Proizvodjaci mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            Integer proizvodjac_id = rs.getInt(index++);
//            String proizvodjac = rs.getString(index++);
            String drzavaProizvodnje = rs.getString(index++);
            int kolicina = rs.getInt(index++);
            //Prijavezavakcinaciju prijavezavakcinaciju= vakcinacijaService.findOne (rs.getString(index++));

            Proizvodjaci proizvodjacVakcine = new Proizvodjaci();

            proizvodjacVakcine.setProizvodjacId(proizvodjac_id);
//            proizvodjacVakcine.setProizvodjac(proizvodjac);
            proizvodjacVakcine.setDrzavaProizvodnje(drzavaProizvodnje);
            proizvodjacVakcine.setKolicina(kolicina);
            //proizvodjacVakcine.setPrijavezavakcinacijus(prijavezavakcinaciju);

            return proizvodjacVakcine;
        }
    }

    @Override
    public Proizvodjaci findOne(String proizvodjac) {
        try {
            String sql = "SELECT * FROM proizvodjaci WHERE proizvodjac = ?";
            return jdbcTemplate.queryForObject(sql, new ProizvodjacRowMapper(), proizvodjac);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Proizvodjaci findOne(int id) {
        try {
            String sql = "SELECT * FROM proizvodjaci WHERE proizvodjac_id = ?";
            return jdbcTemplate.queryForObject(sql, new ProizvodjacRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void smanjiKolicinu(int id) {
        Proizvodjaci p = findOne(id);
        int novaKolicina = p.getKolicina() - 1;
        jdbcTemplate.update("UPDATE proizvodjaci SET kolicina = " + novaKolicina + " WHERE proizvodjac_id = " + id);
    }
}
