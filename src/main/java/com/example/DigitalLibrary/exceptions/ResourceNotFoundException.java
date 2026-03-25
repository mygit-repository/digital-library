package com.example.DigitalLibrary.exceptions;

import com.example.DigitalLibrary.constants.ErrorCode;

public class ResourceNotFoundException extends DigitalLibraryException{
    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
//    public ResourceNotFoundException(String resource, String field, Object value) {
//        super(resource + " not found with " + field + " = " + value);
//    }
}
