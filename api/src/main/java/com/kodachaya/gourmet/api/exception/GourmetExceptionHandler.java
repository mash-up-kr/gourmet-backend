package com.kodachaya.gourmet.api.exception;

import com.kodachaya.gourmet.api.dto.ErrorModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GourmetExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public @ResponseBody ErrorModel handleException(BaseException e) {
        return new ErrorModel(e.getStatusCode(), e.getMessage());
    }

}
