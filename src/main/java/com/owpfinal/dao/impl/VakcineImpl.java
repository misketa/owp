package com.owpfinal.dao.impl;

import com.owpfinal.dao.VakcineDao;
import com.owpfinal.model.Proizvodjaci;
import com.owpfinal.model.Vakcine;
import com.owpfinal.service.ProizvodjacService;
import com.owpfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private class VakcineRowMapper implements RowMapper<Vakcine> {

        @Override
        public Vakcine mapRow(ResultSet rs, int i) throws SQLException {
            int index = 1;
            int vakcina_id = rs.getInt(index++);
            String naziv = rs.getString(index++);
            String kolicina = rs.getString(index++);
            String drzavaProizvodnje = rs.getString(index++);


            Vakcine vakcina = new Vakcine();

            vakcina.setVakcinaId(vakcina_id);
            vakcina.setNaziv(naziv);
            vakcina.setKolicina(kolicina);
            vakcina.setDrzavaProizvodnje(drzavaProizvodnje);


            return vakcina;
        }
    }

    @Override
    public List<Vakcine> findAll() {
        String sql = "SELECT * FROM vakcine";
        return jdbcTemplate.query(sql, new VakcineRowMapper());
    }

    @Override
    public Vakcine findOne(String naziv) {
        try {
            String sql = "SELECT * FROM vakcine WHERE naziv = ?";
            return jdbcTemplate.queryForObject(sql, new VakcineRowMapper(), naziv);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
