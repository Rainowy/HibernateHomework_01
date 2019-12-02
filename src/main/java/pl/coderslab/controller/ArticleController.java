package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.service.ArticleService;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.CategoryService;

import javax.persistence.Tuple;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AuthorService authorService;

    @ModelAttribute("categoriesTuple")
    public List<Tuple> categoriesTuple() {
        return categoryService.fetchCategoriesTuple();
    }

    @ModelAttribute("categoriesEntity")
    public List<Category> categoriesEntity() {
        return categoryService.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authorsEntity() {
        return authorService.findAll();
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Tuple> recentArticles = articleService.showWithRange();

        model.addAttribute("articles", recentArticles);
        model.addAttribute("flag", "showAllArticlesflag");

        return "article";
    }

    @GetMapping("/{category}")

    public String getCategory(Model model, @PathVariable String category) {

//        List<ArticleDTO> articlesCat = articleService.fetchArticlesDTO(category);

        List<Tuple> articlesCat = articleService.articlesBasedOnCategory(category);

        model.addAttribute("articles", articlesCat);
        model.addAttribute("categoryName", category);

        return "article";
    }
    @GetMapping("/add")

    public String addArticle(Model model) {

        model.addAttribute("article", new Article());

        return "article";
    }

    @PostMapping("/postAdd")
    public String postAddArticle(@RequestParam String firstName,
                              String lastName,
                              @ModelAttribute Article article) {

        /** WAŻNE !!! NIE TRZEBA OSOBNO ZAPISYWAĆ ARTYKUŁU PRZED ZAPISANIEM AUTORA, WYSTARCZY NOWY OBIEKT ARTYKUŁ DODAĆ DO NOWO TWORZONEGO AUTORA **/

        articleService.create(article, firstName, lastName);

        return "redirect:/article/";
    }

    @GetMapping("/edit/{id}")
//    @ResponseBody
    public String editArticle(@PathVariable String id,
                              Model model){

        System.out.println("ID TOTOTO " + id);

        Article articleToEdit = articleService.findOne(id);

        model.addAttribute("article",articleToEdit);
        model.addAttribute("edit","editActionFormFLag");

        return "article";
//        return null;
    }

    @PostMapping("/postEdit")
    public String postEditArticle(@ModelAttribute Article article,
                                  Model model){

        articleService.updateArticle(article);

        List<Tuple> recentArticles = articleService.showWithRange();

        model.addAttribute("articles", recentArticles);

        return "article";

        //todo DODAĆ HIDDEN ID W JSP W EDIT ŻEBY UPDATE DZIAŁĄŁO
    }
}
