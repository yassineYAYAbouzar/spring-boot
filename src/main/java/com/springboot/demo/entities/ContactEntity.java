package com.springboot.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ContactEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private UUID contactId;
    private String mobile ;
    private String fix ;
}
