package org.launchcode.Adulting.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hub")
public class HubController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Hub");
        return "hub/index";
    }
}
