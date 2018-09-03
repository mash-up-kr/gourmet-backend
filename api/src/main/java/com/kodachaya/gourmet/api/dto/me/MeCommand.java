package com.kodachaya.gourmet.api.dto.me;

import org.springframework.web.multipart.MultipartFile;

public class MeCommand {

    public String introduce;
    public MultipartFile profile;

    public String getIntroduce() {
        return introduce;
    }

    public MultipartFile getProfile() {
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
