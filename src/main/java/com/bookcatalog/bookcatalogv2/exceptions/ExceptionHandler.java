package com.bookcatalog.bookcatalogv2.exceptions;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bookcatalog.bookcatalogv2.dto.ErrorCode;
import com.bookcatalog.bookcatalogv2.dto.ErrorResponseDto;


/**
 * advice = ini untuk menentukan kapan suatu aspec (potongan baris kode) akan di eksekusi
 * namun @ControllerAdvice ini beda annotasi ini beguna untuk menghandle exception
 * secara global jadi semua exception kita bisa handle di sini
 *
 * @ControllerAdvice adalah spesialisasi anotasi @Component yang memungkinkan untuk menangani
 * pengecualian di seluruh aplikasi dalam satu komponen penanganan global. Itu dapat dilihat
 * sebagai pencegat pengecualian yang dilemparkan oleh metode yang dijelaskan dengan
 * @RequestMapping dan sejenisnya.
 *
 * @ControllerAdvice ini dirancang kusus oleh spring MVC untuk meng advice controller
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        java.util.List<String> details = new ArrayList<String>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDto("invalid data", ErrorCode.INVALID_DATA, details, status));
    }

    // setetelah kita buat mehod ini maka kita harus memapping kan dengan cara meng annotasi @ExceptionHandler() dengan parameter tipe class error nya
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotfoundException.class)
    protected ResponseEntity<ErrorResponseDto> handlerNotFoundException(ResourceNotfoundException ex, WebRequest webRequest){
        java.util.List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        return ResponseEntity.badRequest().body(ErrorResponseDto.of("data not found", details, ErrorCode.DATA_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException ex, WebRequest request) {
        java.util.List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDto("data not found", ErrorCode.INVALID_DATA, details, HttpStatus.BAD_REQUEST));
    }

    /**
     * depends class :
     * ErrorResponseDto -> untuk custom error response nya
     * ResourceNotFoundException -> untuk menangkap error yang disebabkan data tidak dapat ditemukan
     *
     */

}
