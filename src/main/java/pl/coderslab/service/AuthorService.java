package pl.coderslab.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Author;

import javax.persistence.Tuple;
import java.util.List;

@Service
public class AuthorService {


    IGenericDao<Author> dao;

    @Autowired
    public void setDao(IGenericDao<Author> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Author.class);
    }

    public List<Author> findAll() {
        return dao.findAll();
    }

    public Author findOne(String id){

        return dao.findOne(Long.parseLong(id));
    }

    public void create(Author author){
        dao.create(author);
    }

    public void delete(String id){

        dao.deleteAuthorBasedOnIdQuery(Long.parseLong(id));
    }

    public List<Tuple> showAllAuthors(){

        return dao.showAllAuthorsQuery();
    }
}
