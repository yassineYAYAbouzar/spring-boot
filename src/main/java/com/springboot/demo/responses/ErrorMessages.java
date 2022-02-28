package com.springboot.demo.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
public enum ErrorMessages {
    MISSNG_REQUIRED_FIELD("Missing Required field ."),
    RECORD_ALREDY_EXISTS("Record Alredy Exsist"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("No Record Found");
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
