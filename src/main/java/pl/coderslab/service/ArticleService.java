package pl.coderslab.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.DTO.ArticleDTO;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ArticleService {

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    IGenericDao<Article> dao;

    @Autowired
    public void setDao(IGenericDao<Article> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Article.class);
    }

    public List<Article> showALl() {

        return dao.findAll();
    }

    /**
     * Articles in recent section
     */
    public List<Tuple> showWithRange() {

        return dao.findLatestInRange();
    }

    /**
     * Join Fetch JPQL = return entity
     */
    public List<Article> fetchArticles(String name) {

        List<Article> articles = dao.fetchArticles(name);

        return articles;
    }

    /**
     * DTO
     */
    public List<ArticleDTO> fetchArticlesDTO(String name) {

        List<ArticleDTO> articlesDTO = dao.fetchArticlesDTO(name);

        return articlesDTO;
    }

    public List<Tuple> articlesBasedOnCategory(String name) {

        return dao.articlesBasedOnCategory(name);
    }

    public Article findOne(String id) {

        Article articleToFind = dao.findOne(Long.parseLong(id));

        Hibernate.initialize(articleToFind.getCategory());

        return articleToFind;
    }

    public void updateArticle(Article article, String authorFirst, String authorLast) {

        if (validateAuthor(authorFirst, authorLast)) {
            dao.updateAuthorBasedOnIdQuery(article.getId(), authorFirst, authorLast);

        } else {
            dao.deleteAuthorBasedOnIdQuery(article.getId());
            dao.update(article);
        }
    }

    public void create(Article article, String authorFirst, String authorLast) {

        if (validateAuthor(authorFirst, authorLast)) {

            Author author = new Author();
            author.setFirstName(authorFirst);
            author.setLastName(authorLast);
            author.setArticle(article);

            authorService.create(author);
            article.setAuthor(author); /** synchro */

            Long categoryId = article.getCategory().getId();
            Category singleCategory = categoryService.getSingleCategory(categoryId);
            singleCategory.addArticle(article); /** synchro */


        } else {

            article.setAuthor(null); /** synchro */
            dao.create(article);
        }
    }

    public void delete(String id) {

        Article toDelete = dao.findOne(Long.parseLong(id));

        toDelete.getCategory().removeArticle(toDelete); /** synchro */
        toDelete.setAuthor(null); /** synchro */

        /** v bezpośrednie kasowanie artykułu niepotrzebne bo kasuje metoda synchronizująca wyżej(category) */
//        dao.delete(toDelete);
    }

    public boolean validateAuthor(String authorFirst, String authorLast) {
        if (authorFirst.isEmpty()) return false;
        if (authorLast.isEmpty()) return false;
        return true;
    }
}