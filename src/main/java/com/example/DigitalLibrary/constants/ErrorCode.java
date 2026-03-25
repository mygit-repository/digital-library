package com.example.DigitalLibrary.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND),
    REQUEST_ERROR(HttpStatus.BAD_REQUEST),
    REQUEST_ENTITY_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);
    private HttpStatus httpStatus;
}
