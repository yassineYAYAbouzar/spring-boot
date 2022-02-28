package com.springboot.demo.repository;

import com.springboot.demo.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity , Long> {

    UserEntity findByEmail(String email);
}
