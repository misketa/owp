package com.owpfinal.model;

import com.owpfinal.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String id;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String email;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String password;


    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String name;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=13, max=13)
    private String jmbg;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=1, max=30)
    private String address;

    @NotBlank(message = "Ne treba da bude prazno polje")
    @Size(min=9)
    private String phone;

    private String dateOfRegistration;

    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vakcina> vakcine = new ArrayList<>();

    public boolean isAdmin(){
        if (role == Role.ADMIN)
            return true;
        return false;
    }

}
