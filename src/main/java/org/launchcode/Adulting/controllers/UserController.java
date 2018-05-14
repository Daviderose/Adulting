package org.launchcode.Adulting.controllers;

import org.launchcode.Adulting.models.User;
import org.launchcode.Adulting.models.data.UserDao;
import org.launchcode.Adulting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @ModelAttribute @Valid User user, Errors errors ) {

        User username = userRepository.findByUsername(user.getUsername());
        User password = userRepository.findByPassword(user.getPassword());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            model.addAttribute(user);
            return "user/login";
        }

        if (username.equals(user.getPassword()) && password.equals(user.getPassword())) {
            model.addAttribute("username", user.getUsername());
            return "user/index";
        }

        else {
            model.addAttribute("title", "Login");
            model.addAttribute(user);
            return "user/login";
        }

    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String DisplayRegisterForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String ProcessRegisterForm(@ModelAttribute @Valid User newUser,
                                   Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            model.addAttribute(newUser);
            return "user/register";
        }

        newUser.setExperience(0);
        newUser.setLevel(1);
        userDao.save(newUser);
        return "redirect:/hub";
    }
}
