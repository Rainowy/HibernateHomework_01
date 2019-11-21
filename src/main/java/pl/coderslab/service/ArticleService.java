package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;

import java.util.List;

@Service
public class ArticleService {

    IGenericDao<Article> dao;

    @Autowired
    public void setDao(IGenericDao<Article> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Article.class);
    }

    public List<Article> showALl() {
//        System.out.println(range);
//        List<Article> articles;
//        if ( range.equals("null") ) {
//            System.out.println("normal");
//            articles = dao.findAll();
//        } else {
//            System.out.println("enhanced");
//            articles = dao.findLatestInRange(Integer
//                    .parseInt(range));
//
//        }
//
//        System.out.println(articles);
//
//        return articles;

        return dao.findAll();
    }

    public List<Article> showWithRange(){

        return dao.findLatestInRange();
    }
}
