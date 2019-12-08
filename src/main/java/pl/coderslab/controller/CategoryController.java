package pl.coderslab.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.service.ArticleService;
import pl.coderslab.service.CategoryService;

import javax.persistence.Tuple;
import javax.validation.Valid;
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

    @GetMapping("/")
    public String showAllCategories(Model model,
                                    @ModelAttribute("success") String addInfo) {


        model.addAttribute("addSuccess", addInfo);

        return "category";
    }

    @GetMapping("/add")
    public String addCategory(Model model){
//                              @ModelAttribute("success") Object addInfo){

        model.addAttribute("category", new Category());
//        model.addAttribute("addSuccess", addInfo);

        return "category";
    }

    @PostMapping("/postAdd")
    public String postAddCategory(@Valid Category category, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return "category";
        }

        Category createdCategory = categoryService.saveAndReturn(category);

        attributes.addFlashAttribute("success","Category " + createdCategory.getName() +" has been created succcesfully");

        return "redirect:/category/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable String id){

        categoryService.delete(id);

        return "category";

    }


}
