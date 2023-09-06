package com.owpfinal.controller;


import com.owpfinal.dto.RegistrationDto;
import com.owpfinal.enumeration.Role;
import com.owpfinal.exception.UserAlreadyExistException;
import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.User;
import com.owpfinal.service.UserService;
import com.owpfinal.service.VakcinacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value = "/korisnici")
public class UserController {

    public static final String USER_KEY = "loggedUser";

    @Autowired
    private UserService userService;

    @Autowired
    private VakcinacijaService vakcinacijaService;

    @Autowired
    private HttpSession session;

    private String bURL;


    @GetMapping("/hello")
    public void sayHello() {
        System.out.println("HELLo");
    }


    @GetMapping(value = "/registracija")
    public String create(WebRequest request, Model model) {
        RegistrationDto userDto = new RegistrationDto();
        model.addAttribute("user", userDto);
        return "register1";

    }


    @PostMapping(value = "/registracija")
    public ModelAndView create(@ModelAttribute("user") @Valid RegistrationDto userDto) {
        try {
            userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
        return new ModelAndView("login");
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }


    @PostMapping(value = "/login")
    public ModelAndView postLogin(@RequestParam String email, @RequestParam String password, HttpSession session,
                                  HttpServletResponse response) throws IOException {
        try {
            // validacija
            User user = userService.checkLogin(email, password);
            if (user == null) {
                throw new Exception("Neispravno korisničko ime ili lozinka!");
            }


            // prijava
            System.out.println("Ulogovan je : " + user.getName());
            session.setAttribute(UserController.USER_KEY, user);

            return new ModelAndView("vesti");
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

    @GetMapping(value = "/logout")
    public void logout(HttpServletResponse response) throws IOException {
        session.invalidate();

        System.out.println("Korisnik odjavljen");

        response.sendRedirect(bURL);
    }


    @GetMapping(value = "/korisnik")
    public ModelAndView korisnik(HttpSession session) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            ModelAndView mav = new ModelAndView("login");
            return mav;
        }

        User user = userService.findOne(loggedUser.getEmail());
        System.out.println(user);

        ModelAndView result = new ModelAndView("profilKorisnika");
        result.addObject("user", user);
        result.addObject("error", "");

        return result;
    }

    @GetMapping(value = "/prijave/{id}")
    public ModelAndView getAllUsersAplications(@PathVariable("id") String id) {

        User user = userService.findById(4);
        System.out.println(user);
        List<Prijavezavakcinaciju> prijave = vakcinacijaService.findAllByUserId(Integer.parseInt(id));
        System.out.println(prijave);

        ModelAndView result = new ModelAndView("klijentovePrijave");
        result.addObject("user", user);
        result.addObject("prijave", prijave);

        return result;
    }

    @PostMapping(value = "/otkaziPrijavu")
    public ModelAndView otkaziPrijavu(@RequestParam int id, @RequestParam int vakcinacijaId, HttpSession session, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        try {
            // validacija
            User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

            if (user == null || !user.getRole().equals(Role.PACIENT.name())) {
                response.sendRedirect(bURL);
                return null;
            }

            vakcinacijaService.deleteVakcinacija(vakcinacijaId);

            List<Prijavezavakcinaciju> prijave = vakcinacijaService.findAllByUserId(id);
            System.out.println(prijave);
            ModelAndView result = new ModelAndView("klijentovePrijave");

            result.addObject("prijave", prijave);
            result.addObject("user", user);
            return result;
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

    @PostMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String lastname,
                             @RequestParam(required = false) String dateOfBirth,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String jmbg,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) String phone,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String matchingPassword,
                             HttpSession session) throws IOException {


        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            ModelAndView mav = new ModelAndView("login");
            return mav;
        }

        User user = userService.findOne(email);

        user.setName(name);
        user.setLastName(lastname);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setDateOfBirth(df.parse(dateOfBirth));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(email);
        user.setJmbg(jmbg);
        user.setAddress(address);
        user.setPhone(phone);
        user.setPassword(password);

        ModelAndView result = new ModelAndView("profilKorisnika");

        if (!password.equals(matchingPassword)) {
            result.addObject("user", loggedUser);
            result.addObject("error", "passowrdi se ne podudaraju");
            return result;
        } else {
            result.addObject("user", user);
            result.addObject("error", "");
        }


        userService.update(user);

        return result;
    }


}
