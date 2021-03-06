package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;
import pl.coderslab.service.ArticleService;
import pl.coderslab.service.CategoryService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/home")

public class HomePageController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/showAll")

    public String showAll(Model model) {

        List<Article> articles = articleService.showALl();

        model.addAttribute("articles", articles);
        model.addAttribute("flag", "showAllArticlesflag");

        return "home";
    }

    @GetMapping("/showRecent")

    public String showWithRange(Model model) {

        List<Article> articles = articleService.showWithRange();

        List<Category> categories = categoryService.getNameDescription();

        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);

        return "home";
    }

    @PostMapping("/cat")

    public String getCategory(Model model, @RequestParam String category) {

        Set<Article> articlesCat = categoryService.getByName(category);

        model.addAttribute("articles", articlesCat);
        model.addAttribute("categoryName", category);

        return "home";
    }
}
