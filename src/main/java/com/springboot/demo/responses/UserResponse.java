package com.springboot.demo.responses;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
}
