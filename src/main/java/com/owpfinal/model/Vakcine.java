package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vakcine database table.
 * 
 */
@Entity
@NamedQuery(name="Vakcine.findAll", query="SELECT v FROM Vakcine v")
public class Vakcine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vakcina_id")
	private int vakcinaId;

	private String brojDoze;

	@Temporal(TemporalType.DATE)
	private Date datumPrijemaVakcine;

	private String drzavaProizvodnje;

	private String kolicina;

	private String naziv;

	//bi-directional many-to-one association to Prijavezavakcinaciju
	@ManyToOne
	private Prijavezavakcinaciju prijavezavakcinaciju;

	public Vakcine() {
	}

	public int getVakcinaId() {
		return this.vakcinaId;
	}

	public void setVakcinaId(int vakcinaId) {
		this.vakcinaId = vakcinaId;
	}

	public String getBrojDoze() {
		return this.brojDoze;
	}

	public void setBrojDoze(String brojDoze) {
		this.brojDoze = brojDoze;
	}

	public Date getDatumPrijemaVakcine() {
		return this.datumPrijemaVakcine;
	}

	public void setDatumPrijemaVakcine(Date datumPrijemaVakcine) {
		this.datumPrijemaVakcine = datumPrijemaVakcine;
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

	public Prijavezavakcinaciju getPrijavezavakcinaciju() {
		return this.prijavezavakcinaciju;
	}

	public void setPrijavezavakcinaciju(Prijavezavakcinaciju prijavezavakcinaciju) {
		this.prijavezavakcinaciju = prijavezavakcinaciju;
	}

}