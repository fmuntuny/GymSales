package com.cohorte4.cart.controllers;

import com.cohorte4.cart.entities.RatingEntity;
import com.cohorte4.cart.services.ratings.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "*")
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService){this.ratingService = ratingService;}

    @GetMapping
    public ResponseEntity<List<RatingEntity>> getAll(){
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(ratingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RatingEntity> create(@RequestBody RatingEntity ratingEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.save(ratingEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingEntity> update(@PathVariable Long id, @RequestBody RatingEntity ratingEntity){
        return ResponseEntity.ok(ratingService.update(ratingEntity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ratingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
