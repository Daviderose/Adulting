package org.launchcode.Adulting.controllers;

import org.launchcode.Adulting.models.Category;
import org.launchcode.Adulting.models.Quest;
import org.launchcode.Adulting.models.User;
import org.launchcode.Adulting.models.data.CategoryDao;
import org.launchcode.Adulting.models.data.QuestDao;
import org.launchcode.Adulting.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

// Controller for "Fields Of Generosity Page"
@Controller
@RequestMapping("generosity")
public class GenerosityController {

    // Required Dao interfaces
    @Autowired
    private QuestDao questDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;


    // Generosity page filters quests with the category id matching "generosity" id 7
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {

        Category cat = categoryDao.findById(7).orElse(null);
        List<Quest> quests = cat.getQuests();
        model.addAttribute("quests", quests);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("title", "The Fields Of Generosity");
        return "generosity/index";
    }

    // Submitting a checked quest will delete it and add experience points to the logged in user's total.
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String removeQuests(@RequestParam String username, @RequestParam int[] questIds, HttpSession session, Model model ) {

        User foundUser = userDao.findByUsername(username);

        for (int questId : questIds) {
            Quest quest = questDao.findById(questId).orElse(null);
            int points = quest.getPoints();
            foundUser.setExperience(foundUser.getExperience() + points);
            userDao.save(foundUser);
            questDao.deleteById(questId);
        }

        return "redirect:/hub";
    }
}
