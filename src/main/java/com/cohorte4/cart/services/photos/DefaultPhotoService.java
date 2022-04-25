package com.cohorte4.cart.services.photos;

import com.cohorte4.cart.entities.PhotoEntity;
import com.cohorte4.cart.repositories.PhotoRepository;
import com.cohorte4.cart.services.photos.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public class DefaultPhotoService implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public PhotoEntity save(MultipartFile photoFile) throws Exception {
        String fileName = StringUtils.cleanPath(photoFile.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            PhotoEntity photoEntity = new PhotoEntity(fileName, photoFile.getContentType(), photoFile.getBytes());

            return photoRepository.save(photoEntity);
        } catch (Exception e) {
            throw new Exception("Could not store photo: " + fileName, e);
        }

    }

    @Override
    public PhotoEntity getPhotos(Long id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public Stream<PhotoEntity> getAllPhotos() {
        return photoRepository.findAll().stream();
    }

}


