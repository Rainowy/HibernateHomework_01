package pl.coderslab.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Article;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends Serializable>
        extends AbstractJpaDao<T> implements IGenericDao<T> {

    /**Article specific methods*/

//    public List<Article> findLatestInRange() {
//
//         int range =3;
//        return entityManager
//                .createQuery("SELECT a.title, a.createdOn, SUBSTRING(a.content, 1, 200) FROM Article a ORDER BY a.createdOn DESC")
//                .setMaxResults(range)
//                .getResultList();
//
//    }
}

//Dziecko lub rodzic muszą zaimplementować metody z interfejsu. W tym wypadku implementuje abstrakcyjna a tylko 1 metodę dziecko abstrakcyjnej.

