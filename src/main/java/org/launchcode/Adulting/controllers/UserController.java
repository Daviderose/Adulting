package org.launchcode.Adulting.controllers;

import org.hibernate.SessionFactory;
import org.launchcode.Adulting.models.User;
import org.launchcode.Adulting.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Login");
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @RequestParam String username, @RequestParam String password,
                                   RedirectAttributes redirectAttributes, HttpSession session) {

        User foundUser = userDao.findByUsername(username);

        if (username.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Username or Password cannot be empty.");
            return "redirect:/login";
        }

        if (foundUser.getUsername().equals(username) && foundUser.getPassword().equals(password)) {
            session.setAttribute("username", foundUser);
            redirectAttributes.addFlashAttribute("message", "Logged in.");
            return "redirect:/hub";
        } else {
            redirectAttributes.addFlashAttribute("message", "Username or Password is incorrect.");
            return "redirect:/login";
        }

        //TODO validate user
        //TODO Add error messages

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String ProcessRegisterForm(@ModelAttribute @Valid User newUser,
                                   Errors errors, Model model, HttpSession session ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            model.addAttribute(newUser);
            return "user/register";
        }

        newUser.setExperience(0);
        newUser.setLevel(1);
        userDao.save(newUser);
        session.setAttribute("username", newUser);
        return "redirect:/hub";
    }
}
