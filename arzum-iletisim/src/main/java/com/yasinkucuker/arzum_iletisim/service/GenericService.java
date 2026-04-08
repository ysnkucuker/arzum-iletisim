package com.yasinkucuker.arzum_iletisim.service;

import java.util.List;

public interface GenericService<T> {
    T save(T entity);
    T update(Long id, T entity);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
}