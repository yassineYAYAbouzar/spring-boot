package com.springboot.demo.shared.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {
    private Long id;
    private UUID addressId;
    private String city;
    private String country;
    private String street;
    private String postal;
    private String type;
    private UserDto user;
}
