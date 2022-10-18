package com.bookcatalog.bookcatalogv2.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    INVALID_DATA(1), INTERNAL_ERROR(2), NETWORK_ERROR(3), OTHER_ERROR(4), DATA_NOT_FOUND(5);

    protected int statusCode;

    private ErrorCode (int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonValue // => annotasi ini digunnkana untuk menampilkan dalam bentuk integer code nya
    public int code(){
        return this.statusCode;
    }
}
