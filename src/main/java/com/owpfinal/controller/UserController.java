package com.owpfinal.controller;

import com.owpfinal.dto.RegistrationDto;
import com.owpfinal.enumeration.Role;
import com.owpfinal.exception.UserAlreadyExistException;
import com.owpfinal.model.User;
import com.owpfinal.service.UserService;
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
import java.util.Date;

@Controller
@RequestMapping(value = "/korisnici")
public class UserController {

    public static final String USER_KEY = "loggedUser";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    private String bURL;

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

            response.sendRedirect(bURL);
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

    @GetMapping(value = "/logout")
    public void logout(HttpServletResponse response) throws IOException {
        session.invalidate();

        System.out.println("Korisnik odjavljen");

        response.sendRedirect(bURL);
    }


    @GetMapping(value = "/korisnik")
    public ModelAndView korisnik(@RequestParam(required = false) String email, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            response.sendRedirect(bURL);
            return null;
        }*/

        User user = userService.findOne(email);
        System.out.println(user);


        ModelAndView result = new ModelAndView("profilKorisnika");
        //result.addObject("user", loggedUser);
        result.addObject("user", user);
        return result;
    }

    @PostMapping(value = "/edit")
    public void edit(@RequestParam(required = false) String name,
                     @RequestParam(required = false) String lastname,
                     @RequestParam(required = false) Date dateOfBirth,
                     @RequestParam(required = false) String email,
                     @RequestParam(required = false) Role role,
                     @RequestParam(required = false) String dateOfRegistration,
                     @RequestParam(required = false) String jmbg,
                     @RequestParam(required = false) String address,
                     @RequestParam(required = false) String phone,
                     @RequestParam(required = false) String password, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {


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
        user.setRole(role.name());
        user.setDateOfRegistration(dateOfRegistration);
        user.setJmbg(jmbg);
        user.setAddress(address);
        user.setPhone(phone);
        user.setPassword(password);

        userService.update(user);

        response.sendRedirect(bURL + "profilKorisnika");
    }

}
