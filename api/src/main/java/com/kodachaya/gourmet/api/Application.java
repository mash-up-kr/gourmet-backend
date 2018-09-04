package com.kodachaya.gourmet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootApplication
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class }, basePackages = {"com.kodachaya.gourmet.api.entity"})
public class Application {

    public static String UPLOAD_DIR = "upload-dir";
    public static String UPLOAD_DIR_PATH;

    public static void main(String[] args) {

        File f = new File(UPLOAD_DIR);
        f.mkdir();
        UPLOAD_DIR_PATH = f.getAbsolutePath();
        System.out.println("UploadPath: " + UPLOAD_DIR_PATH);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}