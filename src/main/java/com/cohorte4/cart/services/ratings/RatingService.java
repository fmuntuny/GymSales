package com.cohorte4.cart.services.ratings;

import com.cohorte4.cart.entities.RatingEntity;

import java.util.List;

public interface RatingService {

    List<RatingEntity> findAll();

    RatingEntity findById(Long id);

    RatingEntity save(RatingEntity ratingEntity);

    RatingEntity update(RatingEntity ratingEntity, Long id);

    void deleteById(Long id);
}

