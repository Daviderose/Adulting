package org.launchcode.Adulting.controllers;

import org.launchcode.Adulting.models.Category;
import org.launchcode.Adulting.models.Quest;
import org.launchcode.Adulting.models.data.CategoryDao;
import org.launchcode.Adulting.models.data.QuestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("learning")
public class LearningController {

    @Autowired
    private QuestDao questDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        Category cat = categoryDao.findById(5).orElse(null);
        List<Quest> quests = cat.getQuests();
        model.addAttribute("quests", quests);
        model.addAttribute("title", "The Snowy Dunes Of Learning");
        return "learning/index";
    }
}
