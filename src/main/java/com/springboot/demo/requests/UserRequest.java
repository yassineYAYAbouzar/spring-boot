package com.springboot.demo.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    @NotNull
    @Size(min =3)
    private String firstName;
    @NotNull
    @Size(min =3)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8 , max=12)
    private String password;

    private List<AddressRequest> addresses;
}
