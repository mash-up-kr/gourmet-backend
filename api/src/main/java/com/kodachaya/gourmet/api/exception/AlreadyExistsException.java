package com.kodachaya.gourmet.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistsException extends BaseException {

    public AlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT.value(), message);
    }

    public AlreadyExistsException() {
        super(HttpStatus.CONFLICT.value(), "Already Exists");
    }
}
