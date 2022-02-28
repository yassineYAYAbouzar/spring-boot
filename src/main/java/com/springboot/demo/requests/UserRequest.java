package com.springboot.demo.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
