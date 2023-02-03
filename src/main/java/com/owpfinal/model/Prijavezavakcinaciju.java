package com.owpfinal.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prijavezavakcinaciju database table.
 * 
 */
@Entity
@NamedQuery(name="Prijavezavakcinaciju.findAll", query="SELECT p FROM Prijavezavakcinaciju p")
public class Prijavezavakcinaciju implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String jmbg;

	private String ime;

	private String prezime;

	private String vakcina;

	@Column(name = "broj_doze")
	private int brojDoze;

	@Temporal(TemporalType.DATE)
	private Date vreme;

	//bi-directional many-to-one association to Proizvodjaci
	@ManyToOne
	private Proizvodjaci proizvodjaci;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	//bi-directional many-to-one association to Vakcine
	@OneToMany(mappedBy="prijavezavakcinaciju")
	private List<Vakcine> vakcines;

	public Prijavezavakcinaciju() {
	}

	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getVakcina() {
		return this.vakcina;
	}

	public void setVakcina(String vakcina) {
		this.vakcina = vakcina;
	}

	public Proizvodjaci getProizvodjaci() {
		return this.proizvodjaci;
	}

	public void setProizvodjaci(Proizvodjaci proizvodjaci) {
		this.proizvodjaci = proizvodjaci;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Vakcine> getVakcines() {
		return this.vakcines;
	}

	public void setVakcines(List<Vakcine> vakcines) {
		this.vakcines = vakcines;
	}

	public Vakcine addVakcine(Vakcine vakcine) {
		getVakcines().add(vakcine);
		vakcine.setPrijavezavakcinaciju(this);

		return vakcine;
	}

	public Vakcine removeVakcine(Vakcine vakcine) {
		getVakcines().remove(vakcine);
		vakcine.setPrijavezavakcinaciju(null);

		return vakcine;
	}

	public int getBrojDoze() {
		return brojDoze;
	}

	public void setBrojDoze(int brojDoze) {
		this.brojDoze = brojDoze;
	}

	public Date getVreme() {
		return vreme;
	}

	public void setVreme(Date vreme) {
		this.vreme = vreme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}