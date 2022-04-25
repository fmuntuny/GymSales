package com.cohorte4.cart.services.users;

import com.cohorte4.cart.entities.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity save(UserEntity userEntity);

    UserEntity update(UserEntity userEntity);

    void deleteById(Long id);
}
