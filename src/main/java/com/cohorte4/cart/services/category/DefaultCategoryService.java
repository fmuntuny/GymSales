package com.cohorte4.cart.services.category;


import com.cohorte4.cart.entities.CategoryEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCategoryService implements CategoryService{

    private CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity, Long id) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isEmpty()){
            categoryEntity.setId(id);
            categoryRepository.save(categoryEntity);
        }
        return categoryOptional.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new DomainException();
        }
    }
}
