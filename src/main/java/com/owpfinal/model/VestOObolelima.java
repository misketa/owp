package com.owpfinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VestOObolelima {

    @NotBlank
    @Size(min=1)
    private String id;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String oboleliUPoslednja24h;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String testiraniUPoslednja24h;


    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String ukupnoObolelihOdStarta;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String brojHospitalizovanih;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String pacijentiNaRespiratoru;


    private String vremeObjavljivanja;
}
