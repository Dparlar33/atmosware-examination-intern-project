package com.atmosware.managementService.core.utilities.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
