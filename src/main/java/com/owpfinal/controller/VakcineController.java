package com.owpfinal.controller;

import com.owpfinal.model.Vakcine;
import com.owpfinal.service.VakcineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @GetMapping(value = "/vakcina")
    public ModelAndView vakcina(@RequestParam(required = false) String naziv, HttpServletResponse response) throws IOException {


        Vakcine vakcina = vakcineService.findOne(naziv);
        System.out.println(vakcina);

        ModelAndView result = new ModelAndView("pojedinacnaVakcina");
        result.addObject("vakcina", vakcina);
        return result;
    }
}
