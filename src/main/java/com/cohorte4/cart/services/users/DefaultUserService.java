package com.cohorte4.cart.services.users;

import com.cohorte4.cart.entities.UserEntity;
import com.cohorte4.cart.exceptions.DomainException;
import com.cohorte4.cart.repositories.UserRepository;
import com.cohorte4.cart.services.cartitems.DefaultCartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService{

    private UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userEntity.getId());
        if(!optionalUserEntity.isEmpty()){
            userRepository.save(userEntity);
        }
        return optionalUserEntity.orElseThrow(DomainException::new);
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (RuntimeException e){
            throw new DomainException();
        }
    }
}
