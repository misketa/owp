package com.owpfinal.controller;


import com.owpfinal.service.VestiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value="/")
public class MainPageController {

    @Autowired
    private VestiService vestiService;

    @GetMapping
    public ModelAndView index() {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("vesti", vestiService.findAll());
        return mav;
    }
}