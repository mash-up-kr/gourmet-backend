package com.kodachaya.gourmet.dto.me;

public class MeCommand {

    private String introduce;
    private String profile;

    public String getIntroduce() {
        return introduce;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "MeCommand{" +
                "introduce='" + introduce + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
