package com.owpfinal.dao.impl;

import com.owpfinal.dao.VakcinacijaDaO;
import com.owpfinal.model.PrijavaZaVakcinaciju;
import com.owpfinal.model.Vakcina;
import com.owpfinal.model.Vest;
import com.owpfinal.service.VakcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VakcinacijaImpl implements VakcinacijaDaO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VakcineService vakcineService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private class VakcinacijaRowMapper implements RowMapper<PrijavaZaVakcinaciju> {

        @Override
        public PrijavaZaVakcinaciju mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String ime = rs.getString(index++);
            String prezime = rs.getString(index++);
            String jmbg = rs.getString(index++);
            String vakcina = rs.getString(index++);

            PrijavaZaVakcinaciju prijavaZaVakcinaciju = new PrijavaZaVakcinaciju();

            prijavaZaVakcinaciju.setIme(ime);
            prijavaZaVakcinaciju.setPrezime(prezime);
            prijavaZaVakcinaciju.setJmbg(jmbg);
            prijavaZaVakcinaciju.setVakcina(vakcina);

            return prijavaZaVakcinaciju;
        }
    }


    @Override
    public void save(PrijavaZaVakcinaciju prijavaZaVakcinaciju) {
        String sql = "INSERT INTO prijavezavakcinaciju (ime, prezime, jmbg, vakcina) " +
                "  VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, prijavaZaVakcinaciju.getIme(),prijavaZaVakcinaciju.getPrezime(),
                prijavaZaVakcinaciju.getJmbg(), prijavaZaVakcinaciju.getVakcina());
    }

    @Override
    public List<PrijavaZaVakcinaciju> findAll() {
        String sql = "SELECT * FROM prijavezavakcinaciju";
        return jdbcTemplate.query(sql, new VakcinacijaRowMapper());
    }
}
