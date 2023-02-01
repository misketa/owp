package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.PrijavaZaVakcinaciju;
import com.owpfinal.model.User;
import com.owpfinal.model.Vakcina;
import com.owpfinal.model.Vest;
import com.owpfinal.service.VakcinacijaService;
import com.owpfinal.service.VestiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/vakcinacija")
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
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@Valid PrijavaZaVakcinaciju prijavaZaVakcinaciju, HttpServletRequest request, BindingResult bindingResult, String ime, String prezime, String jmbg,
                               Vakcina vakcina,
                               HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

        if (user == null || !user.getRole().equals(Role.PACIENT)) {
            response.sendRedirect(bURL);
            return null;
        }




        prijavaZaVakcinaciju.setIme(prijavaZaVakcinaciju.getIme());
        prijavaZaVakcinaciju.setPrezime(prijavaZaVakcinaciju.getPrezime());
        prijavaZaVakcinaciju.setJmbg(prijavaZaVakcinaciju.getJmbg());
        prijavaZaVakcinaciju.setVakcina(prijavaZaVakcinaciju.getVakcina());


        vakcinacijaService.save(prijavaZaVakcinaciju);
        System.out.println(prijavaZaVakcinaciju);
        response.sendRedirect(bURL + "vesti");
        return null;
    }

    @GetMapping(value = "/prijaveZaVakcinaciju")
    public ModelAndView prijaveZaVakcinaciju( HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(UserController.USER_KEY);

        if (user == null || !user.getRole().equals(Role.PACIENT)) {
            response.sendRedirect(bURL);
            return null;
        }


        List<PrijavaZaVakcinaciju> prijavaZaVakcinacijus = vakcinacijaService.findAll();
        System.out.println(prijavaZaVakcinacijus);
        ModelAndView result = new ModelAndView("pregledPrijavaZaVakcinaciju");

        result.addObject("prijavaZaVakcinacijus", prijavaZaVakcinacijus);
        result.addObject("user", user);
        return result;
    }
}
