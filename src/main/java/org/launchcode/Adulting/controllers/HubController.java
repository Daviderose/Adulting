package org.launchcode.Adulting.controllers;


import org.launchcode.Adulting.models.Category;
import org.launchcode.Adulting.models.Quest;
import org.launchcode.Adulting.models.data.CategoryDao;
import org.launchcode.Adulting.models.data.QuestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("hub")
public class HubController {

    @Autowired
    private QuestDao questDao;

    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "")
    public String index(Model model, HttpSession session ) {

        if (session.getAttribute("username") == (null)) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("title", "The Hub");
        return "hub/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddQuestForm(Model model) {
        model.addAttribute("title", "Add Quest");
        model.addAttribute(new Quest());
        model.addAttribute("categories", categoryDao.findAll());
        return "hub/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddQuestForm(@ModelAttribute @Valid Quest newQuest,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Quest");
            model.addAttribute("categories", categoryDao.findAll());
            return "hub/add";
        }

        Category cat = categoryDao.findById(categoryId).orElse(null);
        newQuest.setCategory(cat);
        questDao.save(newQuest);
        return "redirect:";
    }
}
