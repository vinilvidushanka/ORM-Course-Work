package org.example.dao;

import java.util.List;

public interface CrudDAO <T>  extends SuperDao{
    List<T> getAll() ;
    boolean save(T entity)  ;
    void update(T entity)  ;
    void delete(T entity)  ;
    T search(String id) ;
}
