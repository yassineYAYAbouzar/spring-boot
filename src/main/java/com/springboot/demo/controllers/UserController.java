package com.springboot.demo.controllers;

import com.springboot.demo.entities.UserEntity;
import com.springboot.demo.requests.UserRequest;
import com.springboot.demo.responses.UserResponse;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
class UserController{

    @Autowired
    UserService userService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId){
        UserDto userDto =userService.getUserByUserId(userId);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userDto , userResponse);
        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.OK) ;    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest , userDto);

        UserDto createUser =userService.createUser(userDto);

        UserResponse userResponse  = new UserResponse();

        BeanUtils.copyProperties(createUser,userResponse);
        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.CREATED) ;
    }

    @PatchMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId , @RequestBody UserRequest userRequest){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest , userDto);

        UserDto updater =userService.updateUser(userId,userDto);

        UserResponse userResponse  = new UserResponse();

        BeanUtils.copyProperties(updater,userResponse);
        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.ACCEPTED) ;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deletUser(@PathVariable UUID userId ){
        userService.deletUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}