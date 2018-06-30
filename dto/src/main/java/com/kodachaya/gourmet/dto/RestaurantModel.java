package com.kodachaya.gourmet.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class RestaurantModel {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private LocalDateTime registeredTime;

    public RestaurantModel(int id, String name, double latitude, double longitude, LocalDateTime registeredTime) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.registeredTime = registeredTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(LocalDateTime registeredTime) {
        this.registeredTime = registeredTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantModel that = (RestaurantModel) o;
        return id == that.id &&
                Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(registeredTime, that.registeredTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, latitude, longitude, registeredTime);
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", registeredTime=" + registeredTime +
                '}';
    }
}
