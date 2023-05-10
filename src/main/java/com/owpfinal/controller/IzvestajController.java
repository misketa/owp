package com.owpfinal.controller;

import com.owpfinal.model.Vakcine;
import com.owpfinal.model.VestiOObolelima;
import com.owpfinal.service.VakcineService;
import com.owpfinal.service.VestiOObolelimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/izvestaji")
public class IzvestajController {

    @Autowired
    private VestiOObolelimaService vestiOObolelimaService;

    @GetMapping(value = "/tabelaIzvestaja")
    public ModelAndView izvestajiTable(HttpServletResponse response) throws IOException {

        List<VestiOObolelima> vestiOObolelimas = vestiOObolelimaService.findAll();
        System.out.println(vestiOObolelimas);

        ModelAndView result = new ModelAndView("izvestaji");
        result.addObject("vestiOObolelimas", vestiOObolelimas);

        return result;
    }
}
