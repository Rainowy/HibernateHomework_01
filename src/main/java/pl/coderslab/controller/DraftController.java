package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;
import pl.coderslab.service.ArticleService;
import pl.coderslab.service.CategoryService;
import pl.coderslab.validation.DraftArticle;

import javax.persistence.Tuple;
import javax.validation.groups.Default;
import java.util.List;

@Controller
@RequestMapping("/draft")
public class DraftController {

    ArticleService articleService;
    CategoryService categoryService;

    public DraftController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categoriesEntity")
    public List<Category> categoriesEntity() {
        return categoryService.findAll();
    }

    @GetMapping("/form")
    public String initForm(Model model) {

        Article article = new Article();

        article.setDraft(true);

        model.addAttribute("article", article);

        model.addAttribute("formAction", "/draft/save");

        return "article";
    }

    @PostMapping("/save")
    public String saveForm(Model model,
                           @Validated({DraftArticle.class}) Article article, BindingResult result) {

        if (result.hasErrors()) {

            return "article";
        }
//        if(article.isDraft()) article.setCategory(null);

        articleService.create(article,"","");
        return "home";
    }
}

