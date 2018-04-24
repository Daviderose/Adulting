package org.launchcode.Adulting.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("money")
public class MoneyController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Misty Mountains Of Money");
        return "money/index";
    }
}
