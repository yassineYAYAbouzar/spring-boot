package com.springboot.demo.service;

import com.springboot.demo.entities.UserEntity;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.shared.dto.AddressDto;
import com.springboot.demo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDto userDto;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntityCheck = userRepository.findByEmail(user.getEmail());

        if (userEntityCheck != null) throw new RuntimeException("User already exist");




        for (int i= 0 ; i < user.getAddresses().size() ; i++){
            AddressDto addressDto = user.getAddresses().get(i);

            addressDto.setUser(user);
            addressDto.setAddressId(UUID.randomUUID());
            user.getAddresses().set(i , addressDto);
        }

        UserEntity userEntity = modelMapper.map(user ,UserEntity.class);

        userEntity.setUserId(UUID.randomUUID());

        userEntity.setEncryptedPassword("passwod creypted !");

        userDto = modelMapper.map(userRepository.save(userEntity) ,UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(UUID userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");

        userDto = modelMapper.map(userEntity ,UserDto.class);

        return userDto;
    }

    @Override
    public UserDto updateUser(UUID userId, UserDto user) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userDto = modelMapper.map(userRepository.save(userEntity) ,UserDto.class);
        return userDto;
    }

    @Override
    public void deletUser(UUID userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new RuntimeException("User Not Found ");
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers(int page, int limit) {


        if(page > 0 ) page -= 1;

        List<UserDto> userDtos = new ArrayList<>();
        PageRequest pagebleRequest = PageRequest.of(page,limit);

        Page<UserEntity> userEntityPage = userRepository.findAll(pagebleRequest);
        List<UserEntity> userEntityList = userEntityPage.getContent();

        for (UserEntity userDto:userEntityList){
            UserDto user = modelMapper.map(userDto ,UserDto.class);

            userDtos.add(user);
        }

        return userDtos;
    }
}
