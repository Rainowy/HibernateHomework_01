package pl.coderslab.dao;

import com.google.common.base.Preconditions;
import pl.coderslab.entity.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }


    public T findOne(long id) {
        return null;
    }


    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public void deleteById(long id) {
        entityManager.remove(findOne(id));
    }


    public List<T> getListByName(String name) {
        return null;
    }

    public void create(T entity) {

    }

    public void update(T entity) {
        entityManager.merge(entity);
    }


    public void delete(T entity) {

    }


    public List<Article> findLatestInRange() {

        int range = 3;
//        return entityManager
//                .createQuery("SELECT a.title, a.createdOn, SUBSTRING(a.content, 1, 200) FROM Article a ORDER BY a.createdOn DESC")
//                .setMaxResults(range)
//                .getResultList();

//        return entityManager
//                .createQuery("SELECT a.title, a.createdOn, a.content FROM Article a")
//                .getResultList();

//        entityManager.createQuery("SELECT NEW Article (a.title, a.content) FROM Article a");

//        return  entityManager
//                .createQuery("SELECT a.content, a.title  FROM Article a",Article.class)
//                .getResultList();


        return entityManager.createQuery(
                "SELECT NEW Article (a.title," +
                        " SUBSTRING(a.content,1,200)," +
                        " a.createdOn) FROM Article a")
                .setMaxResults(range)
                .getResultList();

    }
}
