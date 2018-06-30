package com.kodachaya.gourmet.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MenuModel {

    private int id;
    private String name;
    private int price;
    private boolean isWished;
    private SimpleDateFormat wishedAt;

    public MenuModel(int id, String name, int price, boolean isWished, SimpleDateFormat wishedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isWished = isWished;
        this.wishedAt = wishedAt;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isWished() {
        return isWished;
    }

    public void setWished(boolean wished) {
        isWished = wished;
    }

    public SimpleDateFormat getWishedAt() {
        return wishedAt;
    }

    public void setWishedAt(SimpleDateFormat wishedAt) {
        this.wishedAt = wishedAt;
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
                Objects.equals(wishedAt, menuModel.wishedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, isWished, wishedAt);
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isWished=" + isWished +
                ", wishedAt=" + wishedAt +
                '}';
    }
}
