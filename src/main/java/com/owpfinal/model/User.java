package com.owpfinal.model;

import com.owpfinal.enumeration.Role;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "date_of_registration")
    private String dateOfRegistration;

    private String email;

    private String jmbg;

    @Column(name = "last_name")
    private String lastName;

    private String name;

    private String password;

    private String phone;

    private String role;

    //bi-directional many-to-one association to Prijavezavakcinaciju
    @OneToMany(mappedBy = "user")
    private List<Prijavezavakcinaciju> prijavezavakcinacijus;

    //bi-directional many-to-one association to Vesti
    @OneToMany(mappedBy = "user")
    private List<Vesti> vestis;

    //bi-directional many-to-one association to VestiOObolelima
    @OneToMany(mappedBy = "user")
    private List<VestiOObolelima> vestiOObolelimas;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRegistration() {
        return this.dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJmbg() {
        return this.jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Prijavezavakcinaciju> getPrijavezavakcinacijus() {
        return this.prijavezavakcinacijus;
    }

    public void setPrijavezavakcinacijus(List<Prijavezavakcinaciju> prijavezavakcinacijus) {
        this.prijavezavakcinacijus = prijavezavakcinacijus;
    }

    public Prijavezavakcinaciju addPrijavezavakcinacijus(Prijavezavakcinaciju prijavezavakcinacijus) {
        getPrijavezavakcinacijus().add(prijavezavakcinacijus);
        prijavezavakcinacijus.setUser(this);

        return prijavezavakcinacijus;
    }

    public Prijavezavakcinaciju removePrijavezavakcinacijus(Prijavezavakcinaciju prijavezavakcinacijus) {
        getPrijavezavakcinacijus().remove(prijavezavakcinacijus);
        prijavezavakcinacijus.setUser(null);

        return prijavezavakcinacijus;
    }

    public List<Vesti> getVestis() {
        return this.vestis;
    }

    public void setVestis(List<Vesti> vestis) {
        this.vestis = vestis;
    }

    public Vesti addVesti(Vesti vesti) {
        getVestis().add(vesti);
        vesti.setUser(this);

        return vesti;
    }

    public Vesti removeVesti(Vesti vesti) {
        getVestis().remove(vesti);
        vesti.setUser(null);

        return vesti;
    }

    public List<VestiOObolelima> getVestiOObolelimas() {
        return this.vestiOObolelimas;
    }

    public void setVestiOObolelimas(List<VestiOObolelima> vestiOObolelimas) {
        this.vestiOObolelimas = vestiOObolelimas;
    }

    public VestiOObolelima addVestiOObolelima(VestiOObolelima vestiOObolelima) {
        getVestiOObolelimas().add(vestiOObolelima);
        vestiOObolelima.setUser(this);

        return vestiOObolelima;
    }

    public VestiOObolelima removeVestiOObolelima(VestiOObolelima vestiOObolelima) {
        getVestiOObolelimas().remove(vestiOObolelima);
        vestiOObolelima.setUser(null);

        return vestiOObolelima;
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN.name());
    }

    public boolean isPacient() {
        return this.role.equals(Role.PACIENT.name());
    }

    public boolean isDoctor() {
        return this.role.equals(Role.DOCTOR.name());
    }

}