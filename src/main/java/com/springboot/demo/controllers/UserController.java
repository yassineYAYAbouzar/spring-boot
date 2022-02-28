package com.springboot.demo.controllers;

import com.springboot.demo.requests.UserRequest;
import com.springboot.demo.responses.UserResponse;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
class UserController{

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(){
        return "getUser  was called";
    }


    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest , userDto);

        UserDto createUser =userService.createUser(userDto);

        UserResponse userResponse  = new UserResponse();

        BeanUtils.copyProperties(createUser,userResponse);
        return userResponse;
    }

    @PutMapping
    public String updateUser(){
        return "updateUser use was called";
    }
    @DeleteMapping
    public String deletUser(){
        return "deletUser  was called";
    }
}