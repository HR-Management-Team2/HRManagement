package com.hrmanagement.exception;

import lombok.Getter;

@Getter
public class CompanyException extends RuntimeException{
    private final ErrorType errorType;

    public CompanyException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
