package pl.coderslab.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Article;
import pl.coderslab.entity.Category;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends Serializable>
        extends AbstractJpaDao<T> implements IGenericDao<T> {

    /** Article specific methods*/

    public List<Article> findLatestInRange() {

        int range = 3;
        return entityManager.createQuery(
                "SELECT NEW Article (a.title," +
                        " SUBSTRING(a.content,1,200)," +
                        " a.createdOn) FROM Article a")
                .setMaxResults(range)
                .getResultList();
    }

    /** Category specific methods */

    public List<Category> getNameDescription(){

       return  entityManager.createQuery("SELECT NEW Category (c.name, c.description) FROM Category c")
               .getResultList();
    }
}


