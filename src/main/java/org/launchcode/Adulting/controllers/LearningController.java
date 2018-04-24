package org.launchcode.Adulting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("learning")
public class LearningController {
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Snowy Dunes Of Learning");
        return "learning/index";
    }
}
