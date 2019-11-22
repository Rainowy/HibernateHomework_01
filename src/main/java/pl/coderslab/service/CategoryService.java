package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    IGenericDao<Category> dao;


    @Autowired
    public void setDao(IGenericDao<Category> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Category.class);
    }

    public List<Category> getNameDescription() {

        return dao.getNameDescription();

    }

    public Set<Article> getByName(String name){

        Category byName = dao.getByName(name);

        return byName.getArticles();
    }
}
