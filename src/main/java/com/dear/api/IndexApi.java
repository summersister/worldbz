package com.dear.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexApi {


    @RequestMapping({"/","index.html"})
    public ModelAndView index(Model model){
        model.addAttribute("title", "包子");

        return new ModelAndView("index");
    }
}
