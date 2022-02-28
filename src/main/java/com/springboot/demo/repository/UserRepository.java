package com.springboot.demo.repository;

import com.springboot.demo.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity , Long> {

    UserEntity findByEmail(String email);
    UserEntity findByUserId(UUID userId);
}
