package com.d24.hms.dao;

import com.d24.hms.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDao <T extends SuperEntity,ID extends Serializable> extends SuperDao{
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    T search(ID id);
    List<T> getAll();

}
