package com.springboot.demo.responses;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressResponse {
    private UUID addressId;
    private String city;
    private String country;
    private String street;
    private String postal;
    private String type;
}
