package com.cohorte4.cart.services.products;

import com.cohorte4.cart.entities.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> findAll();

    ProductEntity findById(Long id);

    ProductEntity save(ProductEntity productEntity);

    ProductEntity update(ProductEntity productEntity, Long id);

    void deleteById(Long id);
}
