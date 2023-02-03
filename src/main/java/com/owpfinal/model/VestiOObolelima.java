package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vesti_o_obolelima database table.
 * 
 */
@Entity
@Table(name="vesti_o_obolelima")
@NamedQuery(name="VestiOObolelima.findAll", query="SELECT v FROM VestiOObolelima v")
public class VestiOObolelima implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String brojHospitalizovanih;

	private String oboleliUPoslednja24h;

	private String pacijentiNaRespiratoru;

	private String testiraniUPoslednja24h;

	private String ukupnoObolelihOdStarta;

	private String vremeObjavljivanja;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	public VestiOObolelima() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrojHospitalizovanih() {
		return this.brojHospitalizovanih;
	}

	public void setBrojHospitalizovanih(String brojHospitalizovanih) {
		this.brojHospitalizovanih = brojHospitalizovanih;
	}

	public String getOboleliUPoslednja24h() {
		return this.oboleliUPoslednja24h;
	}

	public void setOboleliUPoslednja24h(String oboleliUPoslednja24h) {
		this.oboleliUPoslednja24h = oboleliUPoslednja24h;
	}

	public String getPacijentiNaRespiratoru() {
		return this.pacijentiNaRespiratoru;
	}

	public void setPacijentiNaRespiratoru(String pacijentiNaRespiratoru) {
		this.pacijentiNaRespiratoru = pacijentiNaRespiratoru;
	}

	public String getTestiraniUPoslednja24h() {
		return this.testiraniUPoslednja24h;
	}

	public void setTestiraniUPoslednja24h(String testiraniUPoslednja24h) {
		this.testiraniUPoslednja24h = testiraniUPoslednja24h;
	}

	public String getUkupnoObolelihOdStarta() {
		return this.ukupnoObolelihOdStarta;
	}

	public void setUkupnoObolelihOdStarta(String ukupnoObolelihOdStarta) {
		this.ukupnoObolelihOdStarta = ukupnoObolelihOdStarta;
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