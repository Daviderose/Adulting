package org.launchcode.Adulting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("generosity")
public class GenerosityController {
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Fields Of Generosity");
        return "generosity/index";
    }
}
