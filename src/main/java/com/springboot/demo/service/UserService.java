package com.springboot.demo.service;

import com.springboot.demo.shared.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserByUserId(UUID userId);
    UserDto updateUser(UUID userId ,UserDto user);
    void deletUser(UUID userId);
    List<UserDto> getAllUsers(int page , int limit);
}
