package com.owpfinal.controller;

import com.owpfinal.enumeration.Role;
import com.owpfinal.model.*;
import com.owpfinal.service.VakcineService;
import com.owpfinal.service.VestiOObolelimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/izvestaji")
public class IzvestajController {

    @Autowired
    private VestiOObolelimaService vestiOObolelimaService;

    @Autowired
    private HttpSession session;

    private String bURL;

    @GetMapping(value = "/tabelaIzvestaja")
    public ModelAndView izvestajiTable(HttpServletResponse response) throws IOException {

        List<VestiOObolelima> vestiOObolelimas = vestiOObolelimaService.findAll();
        System.out.println(vestiOObolelimas);

        ModelAndView result = new ModelAndView("izvestaji");
        result.addObject("vestiOObolelimas", vestiOObolelimas);

        return result;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }


        ModelAndView result = new ModelAndView("kreiranjeIzvestaja");
        result.addObject("user", loggedUser);
        result.addObject("izvestaj", new VestiOObolelima());
        result.addObject("error", "");
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView create(@Valid VestiOObolelima vestiOObolelima,BindingResult bindingResult,  String brojHospitalizovanih,
                               String oboleliUPoslednja24h,
                               String pacijentiNaRespiratoru,
                               String testiraniUPoslednja24h,
                               String ukupnoObolelihOdStarta,
                               String vremeObjavljivanja,
                               HttpServletResponse response) throws IOException {

        User loggedUser = (User) session.getAttribute(UserController.USER_KEY);
        if (loggedUser == null || !loggedUser.getRole().equals(Role.ADMIN.name())) {
            response.sendRedirect(bURL);
            return null;
        }

        if (bindingResult.hasErrors()) {
            ModelAndView error = new ModelAndView("failedLogin");
            System.out.println(vestiOObolelima);
            error.addObject("user", loggedUser);
            return error;
        }

        vestiOObolelima.setBrojHospitalizovanih(vestiOObolelima.getBrojHospitalizovanih());
        vestiOObolelima.setOboleliUPoslednja24h(vestiOObolelima.getOboleliUPoslednja24h());
        vestiOObolelima.setPacijentiNaRespiratoru(vestiOObolelima.getPacijentiNaRespiratoru());
        vestiOObolelima.setTestiraniUPoslednja24h(vestiOObolelima.getTestiraniUPoslednja24h());
        vestiOObolelima.setUkupnoObolelihOdStarta(vestiOObolelima.getUkupnoObolelihOdStarta());
        vestiOObolelima.setVremeObjavljivanja(vestiOObolelima.getVremeObjavljivanja());


        vestiOObolelimaService.save(vestiOObolelima);
        response.sendRedirect(bURL + "izvestaji");
        return null;
    }
}
