package pl.coderslab.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.DTO.CategoryDTO;
import pl.coderslab.dao.IGenericDao;
import pl.coderslab.entity.Category;
import javax.persistence.Tuple;
import java.util.List;

@Service
public class CategoryService {

    IGenericDao<Category> dao;


    @Autowired
    public void setDao(IGenericDao<Category> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Category.class);
    }

    public List<Category> findAll(){
        return dao.findAll();
    }

    public List<Category> getNameDescription() {

        return dao.getNameDescription();

    }

    public List<CategoryDTO> fetchCategoriesDTO(){

        return dao.fetchCategoriesDTO();
    }

    public List<Tuple> fetchCategoriesTuple(){

        return dao.getCategoriesTuple();
    }

    public Category findOne(String id){

        return dao.findOne(Long.parseLong(id));
    }
}
