package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vesti database table.
 */
@Entity
@NamedQuery(name = "Vesti.findAll", query = "SELECT v FROM Vesti v")
public class Vesti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String naziv;

    private String sadrzaj;

    private String vremeObjavljivanja;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Vesti() {
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSadrzaj() {
        return this.sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getVremeObjavljivanja() {
        return this.vremeObjavljivanja;
    }

    public void setVremeObjavljivanja(String vremeObjavljivanja) {
        this.vremeObjavljivanja = vremeObjavljivanja;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}