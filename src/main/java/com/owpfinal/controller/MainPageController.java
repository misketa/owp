package com.owpfinal.controller;

import com.owpfinal.model.Vest;
import com.owpfinal.service.VestiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/")
public class MainPageController {

    @Autowired
    private VestiService vestiService;

    @GetMapping
    public ModelAndView index(HttpServletResponse response) throws IOException {


        List<Vest> vesti = vestiService.findAll();

        ModelAndView result = new ModelAndView("index");

        result.addObject("vesti", vesti);
        return result;
    }
}