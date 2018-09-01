package com.kodachaya.gourmet.api.dto.restaurant.command;

import java.util.Optional;

public class RestaurantPostCommand {

    private String name;
    private String address;
    private Optional<Double> latitude;
    private Optional<Double> longitude;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Optional<Double> getLatitude() {
        return latitude;
    }

    public Optional<Double> getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "RestaurantPostCommand{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
