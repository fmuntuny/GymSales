package com.cohorte4.cart.services.products;

import com.cohorte4.cart.entities.ProductEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {

    private ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository){this.productRepository = productRepository;}

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductEntity productEntity, Long id) {
        Optional<ProductEntity> productOptional = productRepository.findById(id);
        if(!productOptional.isEmpty()){
            productEntity.setId(id);
            productRepository.save(productEntity);
        }
        return productOptional.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new DomainException();
        }
    }
}
