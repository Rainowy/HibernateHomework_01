package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import javax.persistence.Tuple;
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

    public List<CategoryDTO> fetchCategoriesDTO(){

        return dao.fetchCategoriesDTO();
    }

    public List<Tuple> fetchCategoriesTuple(){

        return dao.getCategoriesTuple();

    }




}
