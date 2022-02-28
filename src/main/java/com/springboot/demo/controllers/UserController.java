package com.springboot.demo.controllers;
import com.springboot.demo.exceptions.UserException;
import com.springboot.demo.requests.UserRequest;
import com.springboot.demo.responses.ErrorMessages;
import com.springboot.demo.responses.UserResponse;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.OK) ;
    }
    @GetMapping
    public List<UserResponse> getAllUser(@RequestParam(value="page",defaultValue = "1") int page ,@RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<UserResponse> userResponse = new ArrayList<>();

        List<UserDto> users =userService.getAllUsers( page , limit);

        for (UserDto userDto:users){
            UserResponse user = new UserResponse();
            BeanUtils.copyProperties(userDto , user);
            userResponse.add(user);
        }
        return userResponse;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

        if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSNG_REQUIRED_FIELD.getErrorMessage());

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