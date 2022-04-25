package com.cohorte4.cart.services.carts;

import com.cohorte4.cart.entities.CartEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCartService implements CartService {

    private CartRepository cartRepository;

    public DefaultCartService(CartRepository cartRepository){this.cartRepository = cartRepository;}

    @Override
    public List<CartEntity> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public CartEntity findById(Long id) {
        return cartRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public CartEntity save(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    @Override
    public CartEntity update(CartEntity cartEntity) {
        Optional<CartEntity> cartOptional = cartRepository.findById(cartEntity.getId());
        if(!cartOptional.isEmpty()){
            cartRepository.save(cartEntity);
        }
        return cartOptional.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            cartRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new DomainException();
        }
    }
}
