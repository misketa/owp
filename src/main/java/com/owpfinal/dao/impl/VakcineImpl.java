package com.owpfinal.dao.impl;

import com.owpfinal.dao.VakcineDao;
import com.owpfinal.model.ProizvodjacVakcine;
import com.owpfinal.model.Vakcina;
import com.owpfinal.service.ProizvodjacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VakcineImpl implements VakcineDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProizvodjacService proizvodjacService;

    @Autowired
    public void setProizvodjacService(ProizvodjacService proizvodjacService) {
        this.proizvodjacService = proizvodjacService;
    }

    public ProizvodjacService getProizvodjacService() {
        return proizvodjacService;
    }


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private class VakcineRowMapper implements RowMapper<Vakcina> {

        @Override
        public Vakcina mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String naziv = rs.getString(index++);
            String kolicina = rs.getString(index++);
            String drzavaProizvodnje = rs.getString(index++);
            ProizvodjacVakcine proizvodjac = proizvodjacService.findOne(rs.getString(index++));


            Vakcina vakcina = new Vakcina();

            vakcina.setNaziv(naziv);
            vakcina.setKolicina(kolicina);
            vakcina.setDrzavaProizvodnje(drzavaProizvodnje);
            vakcina.setProizvodjacVakcine(proizvodjac);

            return vakcina;
        }
    }

    @Override
    public List<Vakcina> findAll() {
        String sql = "SELECT * FROM vakcine";
        return jdbcTemplate.query(sql, new VakcineRowMapper());
    }

    @Override
    public Vakcina findOne(String naziv) {
        try {
            String sql = "SELECT * FROM vakcine WHERE naziv = ?";
            return jdbcTemplate.queryForObject(sql, new VakcineRowMapper(), naziv);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
