package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProizvodjacVakcine {

    @NotBlank(message = "Ne treba da bude prazno polje")
    private String proizvodjac;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String drzavaProizvodnje;

    private Vakcina vakcina;


}
