package com.example.DigitalLibrary.exceptions;

import com.example.DigitalLibrary.constants.ErrorCode;

public class UnauthorizedException extends DigitalLibraryException{
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
//    public UnauthorizedException(String message) {
//        super(message);
//    }
}
