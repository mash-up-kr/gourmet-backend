package com.kodachaya.gourmet.api.dto.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuModel {

    private int id;
    private String name;
    private Optional<Integer> price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registeredTime;

    private boolean isWished;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime wishedAt;


    public MenuModel() {}

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

    public Optional<Integer> getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = Optional.ofNullable(price);
    }


    public void setPrice(Optional<Integer> price) {
        this.price = price;
    }

    public boolean isWished() {
        return isWished;
    }

    public void setWished(boolean wished) {
        isWished = wished;
    }

    public LocalDateTime getWishedAt() {
        return wishedAt;
    }

    public void setWishedAt(LocalDateTime wishedAt) {
        this.wishedAt = wishedAt;
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
        MenuModel menuModel = (MenuModel) o;
        return id == menuModel.id &&
                price == menuModel.price &&
                isWished == menuModel.isWished &&
                Objects.equals(name, menuModel.name) &&
                Objects.equals(wishedAt, menuModel.wishedAt) &&
                Objects.equals(registeredTime, menuModel.registeredTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, isWished, wishedAt, registeredTime);
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isWished=" + isWished +
                ", wishedAt=" + wishedAt +
                ", registeredTime=" + registeredTime +
                '}';
    }
}
