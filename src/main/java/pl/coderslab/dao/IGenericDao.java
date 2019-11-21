package pl.coderslab.dao;

import java.io.Serializable;

public interface IGenericDao<T extends Serializable> extends IOperations<T> {

    void setClazz(Class<T> clazz);
}
