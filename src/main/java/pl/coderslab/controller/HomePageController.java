package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Car;
import pl.coderslab.service.ArticleService;

import java.util.List;

@Controller
@RequestMapping("/home")

public class HomePageController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/showAll")
//            (value = "/showAll", produces = "text/html; charset=UTF-8")
//    @ResponseBody
    public String showAll(Model model
                                ) {

//        articleService.showALl().stream()
//                .forEach(System.out::println);

//        List<Article> articles;
//        if (range.isEmpty()) {
//            articles = articleService.showALl();
//        }else{
//
//        }

//
//        System.out.println(articleService.showALl());
        List<Article> articles = articleService.showALl();
//
        System.out.println(articles);

//Car.build()
        model.addAttribute("articles", articles);

        return "home";
//        return articles;

//        return articleService.showALl();
//        return Car.build();

//        return articleService.showALl();
    }

    @GetMapping("/dziuba")

    public String showWithRange(Model model){

        List<Article> articles =  articleService.showWithRange();

        System.out.println(articles);

        for(Article a: articles){
            System.out.println(a);
        }
        System.out.println(articles.get(0));
        model.addAttribute("articles", articles);

//        articles.stream()
//                .forEach(System.out::println);

        return "home";

    }
}
