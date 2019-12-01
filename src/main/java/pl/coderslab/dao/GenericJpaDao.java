package pl.coderslab.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import javax.persistence.Tuple;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends Serializable>
        extends AbstractJpaDao<T> implements IGenericDao<T> {

    /**
     * Article specific methods
     */

    public List<Article> findLatestInRange() {

        int range = 3;
        return entityManager.createQuery(
                "SELECT NEW Article (a.title," +
                        " SUBSTRING(a.content,1,200)," +
                        " a.createdOn) FROM Article a")
                .setMaxResults(range)
                .getResultList();
    }

    public List<Article> fetchArticles(String categoryName) {

        /** DZIAŁA */

        List<Article> articles = entityManager.createQuery(
                "select a " +
                        "from Article a " +
                        "join fetch a.category " +
                        "where a.category.name = :category", Article.class)
                .setParameter("category", categoryName)
                .getResultList();

        return articles;

    }

    /**
     * DZIAŁA
     */
    public List<ArticleDTO> fetchArticlesDTO(String categoryName) {

//        List articlesDTO = entityManager.createNativeQuery(
//                "select " +
//                        " a.id as \"id\", " +
//                        " a.content as \"content\"," +
//                        " a.createdOn as \"createdOn\"," +
//                        " a.title as \"title\"," +
//                        " c.name as \"name\"," +
//                        " a.updatedOn as \"updatedOn\"," +
//                        " from article a " +
//                        " left join category c " +
//                        "where c.name = :category", ArticleDTO.class)
//                .setParameter("category", categoryName)
//
//                .getResultList();
//return articlesDTO;


        List<ArticleDTO> articlesDTO = entityManager.createQuery(
                "select new pl.coderslab.DTO.ArticleDTO(" +
                        "a.id , a.content,  a.createdOn, a.title, c.name, a.updatedOn" +
                        " ) " +
                        " from Article a " +
                        "join a.category c " +
                        "where a.category.name = :category", ArticleDTO.class)
                .setParameter("category", categoryName)
                .getResultList();

        return articlesDTO;
    }

    /**
     * Category specific methods
     */

    public List<Category> getNameDescription() {

        return entityManager.createQuery("SELECT NEW Category (c.name, c.description) FROM Category c")
                .getResultList();
    }

    public List<CategoryDTO> fetchCategoriesDTO() {

        List<CategoryDTO> categories = entityManager.createQuery(
                "select new pl.coderslab.DTO.CategoryDTO(" +
                        "c.id , c.description, c.name, a.id" +
                        " ) " +
                        "from Category c " +
                        "join c.articles a", CategoryDTO.class)
                .getResultList();
        return categories;
    }

    public List<Tuple> getCategoriesTuple(){

        List<Tuple> categories = entityManager.createQuery(
                "select " +
                        "c.id as id, " +
                        "c.name as name " +
                        "from Category c", Tuple.class)
                .getResultList();

        return categories;
    }
}

