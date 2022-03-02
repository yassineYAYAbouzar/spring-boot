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
public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private UUID addressId;
    //@Column(nullable = false)
    private String city;
    //@Column(nullable = false)
    private String country;
    //@Column(nullable = false)
    private String street;
    //@Column(nullable = false)
    private String postal;
    //@Column(nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
