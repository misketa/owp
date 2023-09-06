package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.User;
import com.owpfinal.model.Vakcine;
import com.owpfinal.service.VakcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/vakcine")
public class VakcineController {

    @Autowired
    private VakcineService vakcineService;

    @Autowired
    private HttpSession session;

    private String bURL;

    @GetMapping(value = "/tabelaVakcina")
    public ModelAndView vakcineTable(HttpServletResponse response) throws IOException {

        /*User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null) {
            response.sendRedirect(bURL);
            return null;
        }*/

        List<Vakcine> vakcinas = vakcineService.findAll();
        System.out.println(vakcinas);

        ModelAndView result = new ModelAndView("vakcine");
        result.addObject("vakcinas", vakcinas);
        //result.addObject("user", loggedUser);

        return result;
    }

    @GetMapping(value = "/vakcina/{id}")
    public ModelAndView vakcina(@PathVariable("id") String id, HttpServletResponse response) throws IOException {

        Vakcine vakcina = vakcineService.findOne(Integer.parseInt(id));
        System.out.println(vakcina);

        ModelAndView result = new ModelAndView("pojedinacnaVakcina");
        result.addObject("vakcina", vakcina);
        return result;
    }
    @PostMapping(value = "/vakcina/{id}")
    public ModelAndView izmena(@RequestParam(required = false) String naziv, @RequestParam(required = false) String drzavaProizvodnje,
                             @PathVariable(required = false) String id,
                             HttpSession session) throws IOException {

        Vakcine vakcina = vakcineService.findOne(Integer.parseInt(id));
        vakcina.setNaziv(naziv);
        vakcina.setDrzavaProizvodnje(drzavaProizvodnje);

        ModelAndView result = new ModelAndView("pojedinacnaVakcina");
        result.addObject("vakcina", vakcina);
        result.addObject("error", "");

        vakcineService.update(vakcina);
        return result;
    }



    @GetMapping(value = "/create")
    public ModelAndView create(HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }


        ModelAndView result = new ModelAndView("kreiranjeVakcine");
        result.addObject("user", loggedUser);
        result.addObject("vakcina", new Vakcine());
        result.addObject("error", "");
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@Valid Vakcine vakcina, String naziv, String drzavaproizvodnje, String kolicina,
                               HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }

        vakcina.setNaziv(vakcina.getNaziv());
        vakcina.setDrzavaProizvodnje(vakcina.getDrzavaProizvodnje());
        vakcina.setKolicina("0");



        vakcineService.save(vakcina);
        response.sendRedirect(bURL + "vakcine");
        return null;
    }
}

//todo kreiranje izvestaja, kreiranje i izmena vakcine
