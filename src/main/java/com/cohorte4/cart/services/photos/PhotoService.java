package com.cohorte4.cart.services.photos;

import com.cohorte4.cart.entities.PhotoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface PhotoService {

    PhotoEntity save(MultipartFile file) throws Exception;
    PhotoEntity getPhotos(Long id);
    Stream<PhotoEntity> getAllPhotos();

}
