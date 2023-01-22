package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.User;
import com.owpfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/korisnici")
public class UserController {

    public static final String USER_KEY = "loggedUser";

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private HttpSession session;

    private String bURL;

    @PostConstruct
    public void init() {
        bURL = servletContext.getContextPath() + "/";
    }


    /*@GetMapping
    public ModelAndView index(HttpServletResponse response) throws IOException {


        List<Vest> vesti = userService.findAll();

        ModelAndView result = new ModelAndView("index");

        result.addObject("vesti", vesti);
        return result;
    }*/

    @GetMapping(value = "/registracija")
    public ModelAndView create() {
        ModelAndView result = new ModelAndView("register");
        return result;
    }


    @PostMapping(value = "/registracija")
    public ModelAndView create(@Valid User client, BindingResult bindingResult, HttpServletResponse response) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateOfRegistration = dtf.format(now);

        client.setRole(Role.PACIENT);
        client.setDateOfRegistration(dateOfRegistration);

        if (bindingResult.hasErrors()) {
            ModelAndView error = new ModelAndView("register");
            error.addObject("client", client);
            return error;
        }

        userService.save(client);
        response.sendRedirect(bURL);
        return null;
    }

    @PostMapping(value = "/login")
    public ModelAndView postLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String password,
                                  HttpServletResponse response) throws IOException {
        try {
            // validacija
            User loggedUser = userService.checkLogin(email, password);
            if (loggedUser == null) {
                throw new Exception("Neispravno korisničko ime ili lozinka!");
            }


            // prijava
            System.out.println("Ulogovan je : " + loggedUser.getName());
            session.setAttribute(UserController.USER_KEY, loggedUser);

            response.sendRedirect(bURL + "vakcine/");
            return null;
        } catch (Exception ex) {
            // ispis greške
            String message = ex.getMessage();
            if (message.isEmpty()) {
                message = "Neuspešna prijava!";
            }

            // prosleđivanje
            ModelAndView result = new ModelAndView("failedLogin");
            result.addObject("message", message);

            return result;
        }
    }

    @GetMapping(value="/logout")
    public void logout(HttpServletResponse response) throws IOException {
        session.invalidate();

        System.out.println("Korisnik odjavljen");

        response.sendRedirect(bURL);
    }

    @GetMapping(value = "/korisnik")
    public ModelAndView korisnik(@RequestParam String username, HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            response.sendRedirect(bURL);
            return null;
        }

        User user = userService.findOne(username);


        ModelAndView result = new ModelAndView("profilKorisnika");
        result.addObject("user", loggedUser);
        result.addObject("singleUser", user);
        return result;
    }

    @PostMapping(value="/edit")
    public void edit(@RequestParam(required=false) String name,
                     @RequestParam(required=false) String lastname,
                     @RequestParam(required=false) Date dateOfBirth,
                     @RequestParam(required=false) String email,
                     @RequestParam(required=false) Role role,
                     @RequestParam(required=false) String dateOfRegistration,
                     @RequestParam(required=false) String jmbg,
                     @RequestParam(required=false) String address,
                     @RequestParam(required=false) String phone,
                     @RequestParam(required=false) String password ,HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {


        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            response.sendRedirect(bURL);
            return;
        }

        User user = userService.findOne(email);

        user.setName(name);
        user.setLastName(lastname);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
        user.setRole(role);
        user.setDateOfRegistration(dateOfRegistration);
        user.setJmbg(jmbg);
        user.setAddress(address);
        user.setPhone(phone);
        user.setPassword(password);

        userService.update(user);

        response.sendRedirect(bURL + "profilKorisnika");
    }

}
