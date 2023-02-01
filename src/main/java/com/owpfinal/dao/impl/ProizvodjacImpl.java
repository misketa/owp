package com.owpfinal.dao.impl;

import com.owpfinal.dao.ProizvodjacDao;
import com.owpfinal.model.ProizvodjacVakcine;
import com.owpfinal.model.Vakcina;
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


    private class ProizvodjacRowMapper implements RowMapper<ProizvodjacVakcine> {

        @Override
        public ProizvodjacVakcine mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String proizvodjac = rs.getString(index++);
            String drzavaProizvodnje = rs.getString(index++);
            String vakcina = rs.getString(index++);

            ProizvodjacVakcine proizvodjacVakcine = new ProizvodjacVakcine();

            proizvodjacVakcine.setProizvodjac(proizvodjac);
            proizvodjacVakcine.setDrzavaProizvodnje(drzavaProizvodnje);
            proizvodjacVakcine.setVakcina(vakcina);

            return proizvodjacVakcine;
        }
    }

    @Override
    public ProizvodjacVakcine findOne(String proizvodjac) {
        try {
            String sql = "SELECT * FROM proizvodjaci WHERE proizvodjac = ?";
            return jdbcTemplate.queryForObject(sql, new ProizvodjacRowMapper(), proizvodjac);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
