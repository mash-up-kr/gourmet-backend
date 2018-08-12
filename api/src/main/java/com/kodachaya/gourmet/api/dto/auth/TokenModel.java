package com.kodachaya.gourmet.api.dto.auth;

import java.util.Objects;

public class TokenModel {

    private String accessToken;
    private long expiresIn;
    private String refreshToken;

    public TokenModel(String accessToken, long expiresIn, String refreshToken) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenModel that = (TokenModel) o;
        return expiresIn == that.expiresIn &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accessToken, expiresIn, refreshToken);
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
