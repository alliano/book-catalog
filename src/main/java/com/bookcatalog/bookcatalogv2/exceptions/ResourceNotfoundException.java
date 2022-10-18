package com.bookcatalog.bookcatalogv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


//kelas ini nanti nay akan dipanggil jiak suatu request tidak ditemukan atau permintaan yang tidak tersedia
@ResponseStatus(value = HttpStatus.NOT_FOUND) @EqualsAndHashCode(callSuper = false) @Setter @Getter
public class ResourceNotfoundException extends RuntimeException {

    private static final long serialVersionUID = 83264823932L;

    private String message;

    public ResourceNotfoundException(String message){
        super();
        this.message = message;
    }
}
