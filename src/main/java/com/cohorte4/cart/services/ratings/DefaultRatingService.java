package com.cohorte4.cart.services.ratings;

import com.cohorte4.cart.entities.RatingEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultRatingService implements RatingService{

    private RatingRepository ratingRepository;

    public DefaultRatingService(RatingRepository ratingRepository){this.ratingRepository = ratingRepository;}

    @Override
    public List<RatingEntity> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public RatingEntity findById(Long id) {
        return ratingRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public RatingEntity save(RatingEntity ratingEntity) {
        return ratingRepository.save(ratingEntity);
    }

    @Override
    public RatingEntity update(RatingEntity ratingEntity, Long id) {
        Optional<RatingEntity> ratingOptional = ratingRepository.findById(id);
        if(!ratingOptional.isEmpty()){
            ratingEntity.setId(id);
            ratingRepository.save(ratingEntity);
        }
        return ratingOptional.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            ratingRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new DomainException();
        }
    }

}
