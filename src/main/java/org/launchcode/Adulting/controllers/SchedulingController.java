package org.launchcode.Adulting.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule")
public class SchedulingController {
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "The Maze Of Scheduling");
        return "schedule/index";
    }
}
