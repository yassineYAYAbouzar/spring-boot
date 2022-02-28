package com.springboot.demo.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private Date timeStamp;
    private String message;
}
