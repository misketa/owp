package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vakcina {

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String vakcina_id;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String naziv;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1)
    private String kolicina;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String drzavaProizvodnje;

    @OneToOne(mappedBy = "vakcina")
    private ProizvodjacVakcine proizvodjacVakcine;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date datumPrijemaVakcine;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1)
    private String brojDoze;

    @ManyToOne
    @JoinColumn(name = "id", nullable = true)
    @JsonIgnore
    private User user;
}
