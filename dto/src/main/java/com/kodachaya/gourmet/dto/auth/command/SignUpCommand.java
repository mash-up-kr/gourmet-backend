package com.kodachaya.gourmet.dto.auth.command;

public class SignUpCommand {

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
        return "SignUpCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
