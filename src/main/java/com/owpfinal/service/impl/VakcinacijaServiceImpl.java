package com.owpfinal.service.impl;

import com.owpfinal.dao.ProizvodjacDao;
import com.owpfinal.dao.VakcinacijaDaO;
import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.Proizvodjaci;
import com.owpfinal.model.User;
import com.owpfinal.model.Vakcine;
import com.owpfinal.repository.PrijaveRepository;
import com.owpfinal.service.VakcinacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class VakcinacijaServiceImpl implements VakcinacijaService {

    @Autowired
    private VakcinacijaDaO vakcinacijaDaO;

    @Autowired
    private PrijaveRepository prijaveRepository;

    @Autowired
    private ProizvodjacDao proizvodjaciDao;

    @Override
    public void save(Prijavezavakcinaciju prijavaZaVakcinaciju, User user) throws Exception {
        Proizvodjaci proizvodjaci = proizvodjaciDao.findOne(prijavaZaVakcinaciju.getVakcina());
        if (proizvodjaci == null) {
            throw new Exception("Nepoznat proizvodjac vakcine");
        }

        if (proizvodjaci.getKolicina() == 0) {
            throw new Exception("Nema vakcina na stanju");
        }

        /*
        Svaki pacijent može da primi maksimalno 4 vakcine za Covid-19, pri čemu se mora voditi
        računa o tome koju dozu dobija. Svaki pacijent može da primi vakcinu bilo kod proizvođača dok
        god se prati šema: nakon prve vakcine, potrebno je da prođe minimalno 3 meseca pre prijema
        druge, između druge i treće treba da prođe minimalno 6 meseci i između treće i četvrte
        potrebno je da prođe minimalno 3 meseca. (napomena: kada budete testirali uslov da je
        potrebno da prođe određen broj meseci, testirajte da prođe određen broj dana ili minuta).

         */

        List<Prijavezavakcinaciju> svePrijave = vakcinacijaDaO.findAllByUser(user.getId());
        if (svePrijave == null) svePrijave = new ArrayList<>();
        int brojDoze = svePrijave.size();
        if (brojDoze >= 4) {
            throw new Exception("Korisnik sme da primi maksimalno 4 doze");
        }

        for (Prijavezavakcinaciju prijava : svePrijave) {
            if (prijava.getBrojDoze() == brojDoze) {
                long brojDanaOdPoslednjeDoze = getDifferenceDays(prijava.getVreme(), new Date());
                if (brojDoze == 1) {
                    //minimum 3 meseca
                    if (brojDanaOdPoslednjeDoze < 90) {
                        throw new Exception("Nije proslo dovoljno vremena od prijema poslednnje doze vakcine");
                    }
                } else if (brojDoze == 2) {
                    //minimum 6 meseci
                    if (brojDanaOdPoslednjeDoze < 180) {
                        throw new Exception("Nije proslo dovoljno vremena od prijema poslednnje doze vakcine");
                    }
                } else if (brojDoze == 3) {
                    //minimum 3 meseca
                    if (brojDanaOdPoslednjeDoze < 90) {
                        throw new Exception("Nije proslo dovoljno vremena od prijema poslednnje doze vakcine");
                    }
                }
            }
        }

        brojDoze++;
        prijavaZaVakcinaciju.setBrojDoze(brojDoze);
        prijavaZaVakcinaciju.setVreme(new Date());
        prijavaZaVakcinaciju.setProizvodjaci(proizvodjaci);
        prijavaZaVakcinaciju.setUser(user);
        vakcinacijaDaO.save(prijavaZaVakcinaciju);
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public List<Prijavezavakcinaciju> findAll() {
        return vakcinacijaDaO.findAll();
    }

    @Override
    public Prijavezavakcinaciju findOne(String string) {
        return null;
    }

    @Override
    public void dajVakcinu(int id) {
        proizvodjaciDao.smanjiKolicinu(id);
    }

    @Override
    public List<Prijavezavakcinaciju> findAllByUserId(int userId) {
        return vakcinacijaDaO.findAllByUserId(userId);

    }

    @Override
    public void deleteVakcinacija(int id) {
         vakcinacijaDaO.obrisiPrijavu(id);
    }


}
