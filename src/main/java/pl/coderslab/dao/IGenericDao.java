package pl.coderslab.dao;

import pl.coderslab.entity.Category;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> extends IOperations<T> {

    void setClazz(Class<T> clazz);
}
