package com.springboot.demo.service;

import com.springboot.demo.entities.UserEntity;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntityCheck =userRepository.findByEmail(user.getEmail());

        if (userEntityCheck != null) throw  new RuntimeException("User already exist");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user ,userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(UUID.randomUUID());

        UserEntity newUserEntity = userRepository.save(userEntity);
        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(newUserEntity , userDto1);
        return userDto1;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity =userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail() , userEntity.getEncryptedPassword() , new ArrayList<>());
    }
}
