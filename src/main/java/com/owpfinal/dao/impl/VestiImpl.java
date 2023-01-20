package com.owpfinal.dao.impl;

import com.owpfinal.dao.VestiDao;
import com.owpfinal.model.Vest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VestiImpl implements VestiDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class VestiRowMapper implements RowMapper<Vest> {

        @Override
        public Vest mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String naziv = rs.getString(index++);
            String sadrzaj = rs.getString(index++);
            String vremeObjavljivanja = rs.getString(index++);

            Vest vest = new Vest();

            vest.setNaziv(naziv);
            vest.setSadrzaj(sadrzaj);
            vest.setVremeObjavljivanja(vremeObjavljivanja);

            return vest;
        }
    }

    @Override
    public List<Vest> findAll() {
        String sql = "SELECT * FROM vesti";
        return jdbcTemplate.query(sql, new VestiRowMapper());
    }
}
