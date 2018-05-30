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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// Controller for "Halls Of Health" Page
@Controller
@RequestMapping("health")
public class HealthController {

    // Required Dao interfaces
    @Autowired
    private QuestDao questDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    // Health page filters quests with the category id matching "health" id 2
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {

        Category cat = categoryDao.findById(2).orElse(null);
        List<Quest> quests = cat.getQuests();
        model.addAttribute("quests", quests);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("title", "The Halls Of Health");
        return "health/index";
    }

    // Submitting a checked quest will delete it and add experience points to the logged in user's total.
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String removeQuests( @RequestParam String username, @RequestParam int[] questIds, HttpSession session, Model model ) {

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
