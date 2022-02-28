package com.springboot.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private UUID userId;
    @Column(nullable = false , length = 50)
    private String firstName;
    @Column(nullable = false , length = 50)
    private String lastName;
    @Column(nullable = false , length = 120 , unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false)
    private Boolean emailVerificationstatus = false;
}
