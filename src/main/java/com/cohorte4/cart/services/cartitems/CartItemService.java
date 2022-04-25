package com.cohorte4.cart.services.cartitems;

import com.cohorte4.cart.entities.CartItemEntity;

import java.util.List;

public interface CartItemService {

    List<CartItemEntity> findAll();

    CartItemEntity findById(Long id);

    CartItemEntity save(CartItemEntity cartItemEntity);

    CartItemEntity update(CartItemEntity cartItemEntity);

    void deleteById(Long id);
}
