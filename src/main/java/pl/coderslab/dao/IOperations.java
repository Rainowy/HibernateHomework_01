package pl.coderslab.dao;

import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import javax.persistence.Tuple;
import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    void create(final T entity);

    T merge(T entity);

    void  update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    List<T> getListByName(String name);

    public T getByName(String name);

    /**Article specific methods*/

    List<Tuple> findLatestInRange();

    List<Article> fetchArticles(String categoryName);

    List<ArticleDTO> fetchArticlesDTO(String categoryName);

    List<Tuple> articlesBasedOnCategory(String categoryName);

    /**Category specific methods*/

    List<Category> getNameDescription();

    List<CategoryDTO> fetchCategoriesDTO();

    List<Tuple> getCategoriesTuple();

    /**Author specific methods*/

    void deleteAuthorBasedOnIdQuery(Long id);

    void updateAuthorBasedOnIdQuery(Long id, String lastName, String firstName);





}

