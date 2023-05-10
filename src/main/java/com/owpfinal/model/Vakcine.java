package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vakcine database table.
 */
@Entity
@NamedQuery(name = "Vakcine.findAll", query = "SELECT v FROM Vakcine v")
public class Vakcine implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vakcina_id")
    private int vakcinaId;


    private String drzavaProizvodnje;

    private String kolicina;

    private String naziv;


    public Vakcine() {
    }

    public int getVakcinaId() {
        return this.vakcinaId;
    }

    public void setVakcinaId(int vakcinaId) {
        this.vakcinaId = vakcinaId;
    }


    public String getDrzavaProizvodnje() {
        return this.drzavaProizvodnje;
    }

    public void setDrzavaProizvodnje(String drzavaProizvodnje) {
        this.drzavaProizvodnje = drzavaProizvodnje;
    }

    public String getKolicina() {
        return this.kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


}