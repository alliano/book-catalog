package com.bookcatalog.bookcatalogv2.dto;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ErrorResponseDto {

    private Date timeStamp;

    private java.lang.String message;

    private ErrorCode errorCode;

    private java.util.List<String> details;

    private HttpStatus httpStatus;

    public static ErrorResponseDto of(final String message, List<String> details, final ErrorCode errirCode, HttpStatus httpStatus){
        return new ErrorResponseDto(message, errirCode, details, httpStatus);
    }

    public ErrorResponseDto(String message, ErrorCode errorCode,List<String> details, HttpStatus httpStatus){
        super();
        this.details = details;
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = new Date();
    }
}
