package com.springboot.demo.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    private String city;
    private String country;
    private String street;
    private String postal;
    private String type;
}