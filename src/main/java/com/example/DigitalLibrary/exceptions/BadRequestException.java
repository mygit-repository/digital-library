package com.example.DigitalLibrary.exceptions;

import com.example.DigitalLibrary.constants.ErrorCode;

public class BadRequestException extends DigitalLibraryException{
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
