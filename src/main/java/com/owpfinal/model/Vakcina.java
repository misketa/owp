package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vakcina {

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String naziv;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1)
    private String kolicina;

    private ProizvodjacVakcine proizvodjacVakcine;
}
