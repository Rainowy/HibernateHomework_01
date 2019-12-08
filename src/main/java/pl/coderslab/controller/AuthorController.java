package pl.coderslab.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Author;
import pl.coderslab.service.AuthorService;

import javax.persistence.Tuple;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/author")
public class AuthorController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authors")
    public List<Tuple> showAllAuthors(){
        return authorService.showAllAuthors();
    }

    @GetMapping("/")
    public String authorHome(){

        return "author";
    }

    @GetMapping("/add")
    public String addAuthor(Model model){

        model.addAttribute("author", new Author());

        return "author";
    }

    @PostMapping("/postAdd")
    public String postAddAuthor(Model model,
                                @Valid Author author, BindingResult result){
        if(result.hasErrors()){
            return "author";
        }
        System.out.println("dodano");

        return null;
    }
}
