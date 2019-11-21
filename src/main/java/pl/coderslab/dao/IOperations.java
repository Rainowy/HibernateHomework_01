package pl.coderslab.dao;

import pl.coderslab.entity.Article;

import java.io.Serializable;
import java.util.List;


public interface IOperations<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    void create(final T entity);

    void  update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    List<T> getListByName(String name);

    /**Article specific methods*/

    List<Article> findLatestInRange();
    }

