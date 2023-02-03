package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.Prijavezavakcinaciju;
import com.owpfinal.model.User;
import com.owpfinal.model.Vakcine;
import com.owpfinal.service.VakcinacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/vakcinacija")
public class VakcinacijaController {

    @Autowired
    private VakcinacijaService vakcinacijaService;

    @Autowired
    private HttpSession session;

    private String bURL;

    @GetMapping(value = "/create")
    public ModelAndView create(HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            response.sendRedirect(bURL);
            return null;
        }

        ModelAndView result = new ModelAndView("prijavaZaVakcinaciju");
        result.addObject("user", loggedUser);
        result.addObject("prijava", new Prijavezavakcinaciju());
        result.addObject("error", "");
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute("prijava") @Valid Prijavezavakcinaciju prijavaZaVakcinaciju, HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

        if (user == null || !user.getRole().equals(Role.PACIENT.name())) {
            response.sendRedirect(bURL);
            return null;
        }

        try {
            vakcinacijaService.save(prijavaZaVakcinaciju, user);
        } catch (Exception e) {
            ModelAndView result = new ModelAndView("prijavaZaVakcinaciju");
            result.addObject("user", user);
            result.addObject("prijava", new Prijavezavakcinaciju());
            result.addObject("error", e.getMessage());
            return result;
        }
        System.out.println(prijavaZaVakcinaciju);
        response.sendRedirect("vest/vesti");
        return null;
    }

    @GetMapping(value = "/prijaveZaVakcinaciju")
    public ModelAndView prijaveZaVakcinaciju(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

        if (user == null || !user.getRole().equals(Role.DOCTOR.name())) {
            response.sendRedirect(bURL);
            return null;
        }


        List<Prijavezavakcinaciju> prijavaZaVakcinacijus = vakcinacijaService.findAll();
        System.out.println(prijavaZaVakcinacijus);
        ModelAndView result = new ModelAndView("pregledPrijavaZaVakcinaciju");

        result.addObject("prijavaZaVakcinacijus", prijavaZaVakcinacijus);
        result.addObject("user", user);
        return result;
    }


    @PostMapping(value = "/dajVakcinu")
    public ModelAndView dajVakcinu(@RequestParam int id, HttpSession session, HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        try {
            // validacija
            User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

            if (user == null || !user.getRole().equals(Role.DOCTOR.name())) {
                response.sendRedirect(bURL);
                return null;
            }

            vakcinacijaService.dajVakcinu(id);

            List<Prijavezavakcinaciju> prijavaZaVakcinacijus = vakcinacijaService.findAll();
            System.out.println(prijavaZaVakcinacijus);
            ModelAndView result = new ModelAndView("pregledPrijavaZaVakcinaciju");

            result.addObject("prijavaZaVakcinacijus", prijavaZaVakcinacijus);
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
}
