package com.kodachaya.gourmet.dto.auth.command;

import java.util.Objects;

public class SignInCommand {
    private String username;
    private int password;

    public SignInCommand(String username, int password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInCommand that = (SignInCommand) o;
        return password == that.password &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "SignInCommand{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
