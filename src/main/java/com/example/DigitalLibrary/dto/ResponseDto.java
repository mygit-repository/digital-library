package com.example.DigitalLibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    long id;
    String status;
    String message;
    String userRegdNo;
    String token;
    @JsonIgnore
    String jwt;
    String base64Captcha;
    List<?> list;
    Integer page;
    Integer pageSize;
    Integer totalPages;
    Long totalElements;
    Optional<?> data;

}
