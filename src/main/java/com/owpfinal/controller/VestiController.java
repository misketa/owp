package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.User;
import com.owpfinal.model.Vesti;
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
@RequestMapping(value = "/vest")
public class VestiController {

    @Autowired
    private VestiService vestiService;

    @Autowired
    private HttpSession session;

    private String bURL;

    @GetMapping(value = "/vesti")
    public ModelAndView vesti(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(UserController.USER_KEY);
        if (user == null) {
            response.sendRedirect(bURL);
            return null;
        }

        List<Vesti> vests = vestiService.findAll();
        System.out.println(vests);
        ModelAndView result = new ModelAndView("vesti");

        result.addObject("vests", vests);
        result.addObject("user", user);
        return result;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }


        ModelAndView result = new ModelAndView("kreiranjeVesti");
        result.addObject("user", loggedUser);
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@Valid Vesti vest, BindingResult bindingResult, String naziv, String sadrzaj, String vremeObjavljivanja,
                               HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }

        vest.setNaziv(vest.getNaziv());
        vest.setSadrzaj(vest.getSadrzaj());
        vest.setVremeObjavljivanja(vest.getVremeObjavljivanja());
        vest.setUser(vest.getUser());


        if (bindingResult.hasErrors()) {
            ModelAndView error = new ModelAndView("failedLogin");
            System.out.println(vest);
            error.addObject("user", loggedUser);
            return error;
        }

        vestiService.save(vest);
        response.sendRedirect(bURL + "vesti");
        return null;
    }
}
