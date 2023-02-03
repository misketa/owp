package com.owpfinal.dao.impl;

import com.owpfinal.dao.VestiOObolelimaDao;
import com.owpfinal.model.VestiOObolelima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VestiOObolelimaImpl implements VestiOObolelimaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class VestiOObolelimaRowMapper implements RowMapper<VestiOObolelima> {

        @Override
        public VestiOObolelima mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            Integer id = rs.getInt(index++);
            String oboleliUPoslednja24h = rs.getString(index++);
            String testiraniUPoslednja24h = rs.getString(index++);
            String ukupnoObolelihOdStarta = rs.getString(index++);
            String brojHospitalizovanih = rs.getString(index++);
            String pacijentiNaRespiratoru = rs.getString(index++);
            String vremeObjavljivanja = rs.getString(index++);

            VestiOObolelima vestOObolelima = new VestiOObolelima();

            vestOObolelima.setId(id);
            vestOObolelima.setOboleliUPoslednja24h(oboleliUPoslednja24h);
            vestOObolelima.setTestiraniUPoslednja24h(testiraniUPoslednja24h);
            vestOObolelima.setUkupnoObolelihOdStarta(ukupnoObolelihOdStarta);
            vestOObolelima.setBrojHospitalizovanih(brojHospitalizovanih);
            vestOObolelima.setPacijentiNaRespiratoru(pacijentiNaRespiratoru);
            vestOObolelima.setVremeObjavljivanja(vremeObjavljivanja);

            return vestOObolelima;
        }
    }


    @Override
    public List<VestiOObolelima> findAll() {
        String sql = "SELECT * FROM vesti_o_obolelima";
        return jdbcTemplate.query(sql, new VestiOObolelimaRowMapper());
    }
}
