package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProizvodjacVakcine {

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String proizvodjac_id;

    @NotBlank(message = "Ne treba da bude prazno polje")
    private String proizvodjac;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String drzavaProizvodnje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vakcina_id", nullable = true)
    private Vakcina vakcina;


}
