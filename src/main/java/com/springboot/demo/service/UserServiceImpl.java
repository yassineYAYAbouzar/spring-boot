package com.springboot.demo.service;

import com.springboot.demo.entities.UserEntity;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;



    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntityCheck = userRepository.findByEmail(user.getEmail());

        if (userEntityCheck != null) throw new RuntimeException("User already exist");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setUserId(UUID.randomUUID());
        userEntity.setEncryptedPassword("passwod creypted !");
        UserEntity newUserEntity = userRepository.save(userEntity);
        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(newUserEntity, userDto1);
        return userDto1;
    }

    @Override
    public UserDto getUserByUserId(UUID userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity , userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto user) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity userUpdated =userRepository.save(userEntity);
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userUpdated , userDto);
        return userDto;
    }

    @Override
    public void deletUser(UUID userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");
        userRepository.delete(userEntity);
    }
}
