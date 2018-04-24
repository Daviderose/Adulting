package org.launchcode.Adulting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("homeandlawn")
public class HomeAndLawnController {
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Jungles Of Home And Lawn");
        return "homeandlawn/index";
    }
}
