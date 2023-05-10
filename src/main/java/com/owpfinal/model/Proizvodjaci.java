package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proizvodjaci database table.
 */
@Entity
@NamedQuery(name = "Proizvodjaci.findAll", query = "SELECT p FROM Proizvodjaci p")
public class Proizvodjaci implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proizvodjac_id")
    private int proizvodjacId;

    private String drzavaProizvodnje;

    private String proizvodjac;

    private int kolicina;

    //bi-directional many-to-one association to Prijavezavakcinaciju
    @OneToMany(mappedBy = "proizvodjaci")
    private List<Prijavezavakcinaciju> prijavezavakcinacijus;

    public Proizvodjaci() {
    }

    public int getProizvodjacId() {
        return this.proizvodjacId;
    }

    public void setProizvodjacId(int proizvodjacId) {
        this.proizvodjacId = proizvodjacId;
    }

    public String getDrzavaProizvodnje() {
        return this.drzavaProizvodnje;
    }

    public void setDrzavaProizvodnje(String drzavaProizvodnje) {
        this.drzavaProizvodnje = drzavaProizvodnje;
    }

    public String getProizvodjac() {
        return this.proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public List<Prijavezavakcinaciju> getPrijavezavakcinacijus() {
        return this.prijavezavakcinacijus;
    }

    public void setPrijavezavakcinacijus(List<Prijavezavakcinaciju> prijavezavakcinacijus) {
        this.prijavezavakcinacijus = prijavezavakcinacijus;
    }

    public Prijavezavakcinaciju addPrijavezavakcinacijus(Prijavezavakcinaciju prijavezavakcinacijus) {
        getPrijavezavakcinacijus().add(prijavezavakcinacijus);
        prijavezavakcinacijus.setProizvodjaci(this);

        return prijavezavakcinacijus;
    }

    public Prijavezavakcinaciju removePrijavezavakcinacijus(Prijavezavakcinaciju prijavezavakcinacijus) {
        getPrijavezavakcinacijus().remove(prijavezavakcinacijus);
        prijavezavakcinacijus.setProizvodjaci(null);

        return prijavezavakcinacijus;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}