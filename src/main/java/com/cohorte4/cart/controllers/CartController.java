package com.cohorte4.cart.controllers;

import com.cohorte4.cart.entities.CartEntity;
import com.cohorte4.cart.services.carts.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*" )
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){this.cartService = cartService;}

    @GetMapping
    public ResponseEntity<List<CartEntity>> getAll(){
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(cartService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CartEntity> create(@RequestBody CartEntity cartEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.save(cartEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartEntity> update(@PathVariable Long id, @RequestBody CartEntity cartEntity){
        return ResponseEntity.ok(cartService.update(cartEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        cartService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
