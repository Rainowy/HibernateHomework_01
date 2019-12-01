package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.entity.Article;
import pl.coderslab.service.ArticleService;
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

    @ModelAttribute("categories")
    public List<Tuple> categories(){
        return categoryService.fetchCategoriesTuple();
    }

    @GetMapping("/")
    public String home(Model model){

        List<Article> recentArticles = articleService.showWithRange();

        model.addAttribute("articles", recentArticles);
        model.addAttribute("flag", "showAllArticlesflag");

        return "article";
    }

    @GetMapping("/{category}")

    public String getCategory(Model model, @PathVariable String category){

        List<ArticleDTO> articlesCat = articleService.fetchArticlesDTO(category);

        model.addAttribute("articles", articlesCat);
        model.addAttribute("categoryName", category);

        return "article";
    }
}
