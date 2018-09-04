package com.kodachaya.gourmet.api.dto.menu.command;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public class MenuPostCommand {

    private String name;
    private Optional<Integer> price;

    public String getName() {
        return name;
    }

    public Optional<Integer> getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MenuPostCommand{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
