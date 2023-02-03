package com.owpfinal.dao.impl;

import com.owpfinal.dao.VakcinacijaDaO;
import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.Proizvodjaci;
import com.owpfinal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class VakcinacijaImpl implements VakcinacijaDaO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private class VakcinacijaRowMapper implements RowMapper<Prijavezavakcinaciju> {

        @Override
        public Prijavezavakcinaciju mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            String ime = rs.getString(index++);
            String prezime = rs.getString(index++);
            String jmbg = rs.getString(index++);
            String vakcina = rs.getString(index++);
            int userId = rs.getInt(index++);
            int proizvodjacId = rs.getInt(index++);
            Date vreme = rs.getDate(index++);
            int brojDoze = rs.getInt(index++);
            int id = rs.getInt(index++);

            Prijavezavakcinaciju prijavaZaVakcinaciju = new Prijavezavakcinaciju();

            prijavaZaVakcinaciju.setIme(ime);
            prijavaZaVakcinaciju.setPrezime(prezime);
            prijavaZaVakcinaciju.setJmbg(jmbg);
            prijavaZaVakcinaciju.setVakcina(vakcina);
            prijavaZaVakcinaciju.setVreme(vreme);
            prijavaZaVakcinaciju.setBrojDoze(brojDoze);
            prijavaZaVakcinaciju.setId(id);
            Proizvodjaci p = new Proizvodjaci();
            p.setProizvodjacId(proizvodjacId);
            prijavaZaVakcinaciju.setProizvodjaci(p);
            User u = new User();
            u.setId(userId);
            prijavaZaVakcinaciju.setUser(u);
            return prijavaZaVakcinaciju;
        }
    }


    @Override
    public void save(Prijavezavakcinaciju prijavaZaVakcinaciju) {
        String sql = "INSERT INTO prijavezavakcinaciju (ime, prezime, jmbg, vakcina, users_id, proizvodjaci_proizvodjac_id, vreme, broj_doze) " +
                "  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, prijavaZaVakcinaciju.getIme(), prijavaZaVakcinaciju.getPrezime(),
                prijavaZaVakcinaciju.getJmbg(), prijavaZaVakcinaciju.getVakcina(),
                prijavaZaVakcinaciju.getUser().getId(), prijavaZaVakcinaciju.getProizvodjaci().getProizvodjacId(),
                prijavaZaVakcinaciju.getVreme(), prijavaZaVakcinaciju.getBrojDoze());
    }

    @Override
    public List<Prijavezavakcinaciju> findAll() {
        String sql = "SELECT * FROM prijavezavakcinaciju";
        return jdbcTemplate.query(sql, new VakcinacijaRowMapper());
    }

    @Override
    public List<Prijavezavakcinaciju> findAllByUser(int userId) {
        String sql = "SELECT * FROM prijavezavakcinaciju WHERE users_id = " + userId;
        return jdbcTemplate.query(sql, new VakcinacijaRowMapper());
    }


}
