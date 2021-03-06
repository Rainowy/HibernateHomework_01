package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Article;
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

        return dao.findAll();
    }

    public List<Article> showWithRange(){

        return dao.findLatestInRange();
    }
}
