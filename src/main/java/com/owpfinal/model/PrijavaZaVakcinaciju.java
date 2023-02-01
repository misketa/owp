package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrijavaZaVakcinaciju {

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String ime;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String prezime;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=13, max=13)
    private String jmbg;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=13, max=13)
    private String vakcina;
}
