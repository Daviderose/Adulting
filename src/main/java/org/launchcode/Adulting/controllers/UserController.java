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

// Controller for User object
@Controller
@RequestMapping("")
public class UserController {

    // required dao interface
    @Autowired
    private UserDao userDao;

    // display login page
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Login");
        return "user/login";
    }

    // handle login request
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(Model model, @RequestParam String username, @RequestParam String password,
                                   RedirectAttributes redirectAttributes, HttpSession session) {

        // search for user with given username
        User foundUser = userDao.findByUsername(username);

        // check for empty fields, returns error if so.
        if (username.isEmpty() || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Username or Password cannot be empty.");
            return "redirect:/login";
        }

        // check for valid username and password
        if (foundUser.getUsername().equals(username) && foundUser.getPassword().equals(password)) {
            session.setAttribute("username", foundUser);

            // Set users level according to experience points
            int points = foundUser.getExperience();
            int level = 1;

            if (points < 200 ) {
                level = 1;
            }

            else if (points < 500) {
                level = 2;
            }

            else if (points < 800) {
                level = 3;
            }

            else if (points < 1200) {
                level = 4;
            }

            else if (points < 1500) {
                level = 5;
            }

            else if (points < 1900) {
                level = 6;
            }

            else if (points < 2300) {
                level = 7;
            }

            else if (points < 2800) {
                level = 8;
            }

            else if (points < 3500) {
                level = 9;
            }

            else {
                level = 10;
            }

            foundUser.setLevel(level);
            userDao.save(foundUser);

            // brings user to the hub upon successful login
            redirectAttributes.addFlashAttribute("message", "Logged in.");
            return "redirect:/hub";
        } else {
            redirectAttributes.addFlashAttribute("message", "Username or Password is incorrect.");
            return "redirect:/login";
        }
    }

    // log out user by clearing http session
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // display register page
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new User());
        return "user/register";
    }

    // process a new user account
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String ProcessRegisterForm(@ModelAttribute @Valid User newUser,
                                   Errors errors, Model model, HttpSession session ) {

        // validation
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            model.addAttribute(newUser);
            return "user/register";
        }

        // create account in database with a level of 1 and 0 experience points.
        newUser.setExperience(0);
        newUser.setLevel(1);
        userDao.save(newUser);
        session.setAttribute("username", newUser);
        return "redirect:/hub";
    }
}
