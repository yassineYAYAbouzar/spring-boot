package com.springboot.demo.controllers;
import com.springboot.demo.exceptions.UserException;
import com.springboot.demo.requests.UserRequest;
import com.springboot.demo.responses.ErrorMessages;
import com.springboot.demo.responses.UserResponse;
import com.springboot.demo.service.UserService;
import com.springboot.demo.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
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
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserDto userDto;


    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId){

        userDto =userService.getUserByUserId(userId);

        UserResponse userResponse = modelMapper.map(userDto ,UserResponse.class);
        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.OK) ;

    }


    @GetMapping
    public List<UserResponse> getAllUser(@RequestParam(value="page",defaultValue = "1") int page ,@RequestParam(value="limit" ,defaultValue = "15") int limit ){
        List<UserResponse> userResponse = new ArrayList<>();

        List<UserDto> users =userService.getAllUsers( page , limit);

        for (UserDto userDto:users){
            UserResponse user= modelMapper.map(userDto ,UserResponse.class);

            userResponse.add(user);
        }
        return userResponse;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

        if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSNG_REQUIRED_FIELD.getErrorMessage());


        userDto = modelMapper.map(userRequest ,UserDto.class);


        UserResponse userResponse = modelMapper.map(userService.createUser(userDto) ,UserResponse.class);


        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.CREATED) ;
    }

    @PatchMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId , @RequestBody UserRequest userRequest){

        userDto = modelMapper.map(userRequest ,UserDto.class);

        UserResponse userResponse  = modelMapper.map(userService.updateUser(userId,userDto) ,UserResponse.class);

        return new ResponseEntity<UserResponse>( userResponse , HttpStatus.ACCEPTED) ;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deletUser(@PathVariable UUID userId ){
        userService.deletUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}