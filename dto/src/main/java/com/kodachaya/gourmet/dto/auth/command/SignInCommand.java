package com.kodachaya.gourmet.dto.auth.command;


public class SignInCommand {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SignInCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
