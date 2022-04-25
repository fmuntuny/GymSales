package com.cohorte4.cart.controllers;

import com.cohorte4.cart.entities.CartItemEntity;
import com.cohorte4.cart.services.cartitems.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
@CrossOrigin(origins = "*")
public class CartItemController {

    private CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService){this.cartItemService = cartItemService;}

    @GetMapping
    public ResponseEntity<List<CartItemEntity>> getAll(){
        return ResponseEntity.ok(cartItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(cartItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CartItemEntity> create(@RequestBody CartItemEntity cartItemEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.save(cartItemEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemEntity> update(@PathVariable Long id, @RequestBody CartItemEntity cartItemEntity){
        return ResponseEntity.ok(cartItemService.update(cartItemEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        cartItemService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
