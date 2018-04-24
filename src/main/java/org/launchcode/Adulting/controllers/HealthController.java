package org.launchcode.Adulting.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("health")
public class HealthController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Halls Of Health");
        return "health/index";
    }
}
