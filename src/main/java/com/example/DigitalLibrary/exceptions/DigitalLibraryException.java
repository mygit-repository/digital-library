package com.example.DigitalLibrary.exceptions;

import com.example.DigitalLibrary.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DigitalLibraryException extends RuntimeException{
    private ErrorCode errorCode;
    private  String message;
}
