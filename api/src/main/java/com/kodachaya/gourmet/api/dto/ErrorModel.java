package com.kodachaya.gourmet.api.dto;

public class ErrorModel {

    private int code;
    private String message;

    public ErrorModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
