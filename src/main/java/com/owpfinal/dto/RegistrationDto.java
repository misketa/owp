package com.owpfinal.dto;

import com.owpfinal.validation.PasswordMatches;
import com.owpfinal.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@PasswordMatches
public class RegistrationDto {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String dateOfBirth;

    @NotNull
    @NotEmpty
    private String jmbg;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String phone;
}
