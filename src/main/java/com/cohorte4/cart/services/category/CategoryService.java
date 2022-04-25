package com.cohorte4.cart.services.category;

import com.cohorte4.cart.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> findAll();

    CategoryEntity findById(Long id);

    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity update(CategoryEntity categoryEntity, Long id);

    void deleteById(Long id);
}
