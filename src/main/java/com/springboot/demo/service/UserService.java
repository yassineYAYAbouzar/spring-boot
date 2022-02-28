package com.springboot.demo.service;

import com.springboot.demo.shared.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserByUserId(UUID userId);
    UserDto updateUser(UUID userId ,UserDto user);
    void deletUser(UUID userId);
}
