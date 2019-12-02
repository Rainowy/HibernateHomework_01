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
        return entityManager.find(clazz, id);
    }


    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public T getByName(String name) {

        Query query = entityManager.createQuery("from " + clazz.getName() + " where name =:name");

        query.setParameter("name", name);

        return (T) query.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }



    public void deleteById(long id) {
        entityManager.remove(findOne(id));
    }


    public List<T> getListByName(String name) {
        return null;
    }

    public void create(T entity) {
        entityManager.persist(entity);


    }

    public T merge(T entity) {
        entityManager.merge(entity);

        return entity;
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }


    public void delete(T entity) {

    }
}
