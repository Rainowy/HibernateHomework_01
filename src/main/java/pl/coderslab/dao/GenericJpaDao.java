package pl.coderslab.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;

import javax.persistence.Query;
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

    public List<Tuple> findLatestInRange() {

        int range = 3;
        return entityManager.createQuery(
                "select " +
                        "a.id as id, " +
                        "a.title as title, " +
                        "SUBSTRING(a.content,1,200) as content, " +
                        "a.createdOn as created, " +
                        "a.updatedOn as updated " +
                        "from Article a", Tuple.class)
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

    public List<ArticleDTO> fetchArticlesDTO(String categoryName) {

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

    public List<Tuple> articlesBasedOnCategory(String categoryName) {


        return entityManager.createQuery(
                "select " +
                        "a.id as id, " +
                        "a.title as title, " +
                        "a.content as content, " +
                        "a.createdOn as created, " +
                        "a.updatedOn as updated, " +
                        "c.name as catName " +
                        "from Article a " +
                        "join a.category c " +
                        "where a.category.name = :category", Tuple.class)
                .setParameter("category", categoryName)
                .getResultList();
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

    public List<Tuple> getCategoriesTuple() {

        List<Tuple> categories = entityManager.createQuery(
                "select " +
                        "c.id as id, " +
                        "c.name as name " +
                        "from Category c", Tuple.class)
                .getResultList();

        return categories;
    }

    public Category getSingleCategory(Long id) {

//        Author a = entityManager.createQuery("SELECT a FROM Author a left JOIN FETCH a.books  WHERE a.id = 1", Author.class).getSingleResult();

        return entityManager.createQuery(
                "select c from Category c " +
                        "join fetch c.articles " +
                        "where c.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult();


    }

    /**
     * Author specific methods
     */

    public void deleteAuthorBasedOnIdQuery(Long id) {

        entityManager.createQuery("delete from Author a  where a.article.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void updateAuthorBasedOnIdQuery(Long id, String lastName, String firstName) {
        entityManager.createQuery("update Author a set              a.firstName = :firstName, " +
                "a.lastName = :lastName " +
                "where a.article.id = :id")
                .setParameter("id", id)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .executeUpdate();
    }

    public List<Tuple> showAllAuthorsQuery() {

        return entityManager.createQuery("select " +
                "a.id as id, " +
                "a.firstName as firstName, " +
                "a.lastName as lastName " +
                "from Author a", Tuple.class)
                .getResultList();
    }
}

