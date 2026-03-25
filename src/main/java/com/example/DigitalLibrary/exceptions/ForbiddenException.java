package com.example.DigitalLibrary.exceptions;

import com.example.DigitalLibrary.constants.ErrorCode;

public class ForbiddenException extends DigitalLibraryException{

    public ForbiddenException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
