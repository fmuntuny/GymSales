package com.cohorte4.cart.services.cartitems;

import com.cohorte4.cart.entities.CartItemEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCartItemService implements CartItemService{

    private CartItemRepository cartItemRepository;

    public DefaultCartItemService(CartItemRepository cartItemRepository){this.cartItemRepository = cartItemRepository;}

    @Override
    public List<CartItemEntity> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItemEntity findById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public CartItemEntity save(CartItemEntity cartItemEntity) {
        return cartItemRepository.save(cartItemEntity);
    }

    @Override
    public CartItemEntity update(CartItemEntity cartItemEntity) {
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findById(cartItemEntity.getId());
        if(!optionalCartItem.isEmpty()){
            cartItemRepository.save(cartItemEntity);
        }
        return optionalCartItem.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            cartItemRepository.deleteById(id);
        } catch (RuntimeException e){
            throw new DomainException();
        }
    }
}
