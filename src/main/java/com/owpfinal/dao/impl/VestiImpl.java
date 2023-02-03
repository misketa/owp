package com.owpfinal.dao.impl;

import com.owpfinal.dao.VestiDao;
import com.owpfinal.model.Vesti;
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

    private class VestiRowMapper implements RowMapper<Vesti> {

        @Override
        public Vesti mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String naziv = rs.getString(index++);
            String sadrzaj = rs.getString(index++);
            String vremeObjavljivanja = rs.getString(index++);

            Vesti vest = new Vesti();

            vest.setNaziv(naziv);
            vest.setSadrzaj(sadrzaj);
            vest.setVremeObjavljivanja(vremeObjavljivanja);

            return vest;
        }
    }

    @Override
    public List<Vesti> findAll() {
        String sql = "SELECT * FROM vesti";
        return jdbcTemplate.query(sql, new VestiRowMapper());
    }

    @Override
    public void save(Vesti vest) {
        String sql = "INSERT INTO vesti (naziv, sadrzaj, vremeObjavljivanja) " +
                "  VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, vest.getNaziv(), vest.getSadrzaj(), vest.getVremeObjavljivanja());
    }
}
