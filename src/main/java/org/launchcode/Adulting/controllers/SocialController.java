package org.launchcode.Adulting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("social")
public class SocialController {
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Social Arena");
        return "social/index";
    }
}
