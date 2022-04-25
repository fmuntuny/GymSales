package com.cohorte4.cart.services.carts;

import com.cohorte4.cart.entities.CartEntity;

import java.util.List;

public interface CartService {

    List<CartEntity> findAll();

    CartEntity findById(Long id);

    CartEntity save(CartEntity cartEntity);

    CartEntity update(CartEntity cartEntity);

    void deleteById(Long id);
}
