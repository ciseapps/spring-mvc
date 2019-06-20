package com.cise.cms.core.applications.base;

import org.springframework.data.domain.Page;
import java.util.List;

public interface BaseService<T> {

    T createOrUpdate(T o);

    void delete(T o);

    long count();

    T findById(long id);

    List<T> findAll();

    Page<T> page(int page);

    Page<T> page(String search, int page);

}
