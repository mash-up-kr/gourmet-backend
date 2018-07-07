package com.kodachaya.gourmet.dto.auth.command;

public class RefreshCommand {

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshCommand{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
