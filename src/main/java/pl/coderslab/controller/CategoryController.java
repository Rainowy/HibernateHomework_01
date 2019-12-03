package pl.coderslab.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.service.ArticleService;
import pl.coderslab.service.CategoryService;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/category")
public class CategoryController {

    CategoryService categoryService;
    ArticleService articleService;

    public CategoryController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @ModelAttribute("categories")
    public List<Tuple> categories(){
        return categoryService.fetchCategoriesTuple();
    }

    @GetMapping("/showAll")
    public String showAllCategories() {

        return "category";
    }
}
