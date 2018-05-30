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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.Clock;
import java.time.ZoneId;

// Controller for "The Hub" Page
@Controller
@RequestMapping("hub")
public class HubController {

    // Required Dao interfaces
    @Autowired
    private QuestDao questDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    // Shows list of different quest zones
    @RequestMapping(value = "")
    public String index(Model model, HttpSession session ) {

        // Check for logged in user, return login page if not.
        if (session.getAttribute("username") == (null)) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("title", "The Hub");
        return "hub/index";
    }

    // Fully adds a new quest, only an administrator will get functionality of this page.
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddQuestForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        model.addAttribute("title", "Add Quest");
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute(new Quest());
        model.addAttribute("categories", categoryDao.findAll());
        return "hub/add";
    }

    // Adds quest to database
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddQuestForm(@ModelAttribute @Valid Quest newQuest,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model, RedirectAttributes redirectAttributes) {

        // Check for valid entry, return to add page if not.
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Quest");
            model.addAttribute("categories", categoryDao.findAll());
            redirectAttributes.addFlashAttribute("message","Name must be at least 3 characters.");
            return "redirect:/hub/add";
        }

        Category cat = categoryDao.findById(categoryId).orElse(null);
        newQuest.setCategory(cat);
        questDao.save(newQuest);
        return "redirect:";
    }

    // Allows limited editing of a quest
    @RequestMapping(value = "edit/{questId}", method = RequestMethod.GET)
    public String displayEditQuestForm(Model model, @PathVariable int questId, HttpSession session ) {
        model.addAttribute("username", session.getAttribute("username"));
        Quest quest = questDao.findById(questId).orElse(null);
        model.addAttribute("title", "Edit Quests");
        model.addAttribute("name", quest.getName());
        model.addAttribute("questId", quest.getId());
        model.addAttribute("categories", categoryDao.findAll());

        return "hub/edit";
    }

    // Update quest with changed values "name" and "Category".
    @RequestMapping(value = "edit/{questId}", method = RequestMethod.POST)
    public String processEditQuestForm(@PathVariable int questId, @RequestParam String name,
                                       @RequestParam int categoryId ) {
        Quest quest = questDao.findById(questId).orElse(null);
        Category cat = categoryDao.findById(categoryId).orElse(null);
        quest.setName(name);
        quest.setCategory(cat);
        questDao.save(quest);
        return "redirect:/hub";
    }
}
