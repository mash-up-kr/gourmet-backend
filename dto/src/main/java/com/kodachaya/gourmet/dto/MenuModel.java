package com.kodachaya.gourmet.dto;

import java.util.Date;
import java.util.Objects;

public class MenuModel {

    private int id;
    private String name;
    private int price;
    private String restaurantId;
    private Date createdAt;

    public MenuModel(int id, String name, int price, String restaurantId, Date createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.createdAt = createdAt;
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuModel menuModel = (MenuModel) o;
        return id == menuModel.id &&
                price == menuModel.price &&
                Objects.equals(name, menuModel.name) &&
                Objects.equals(restaurantId, menuModel.restaurantId) &&
                Objects.equals(createdAt, menuModel.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, restaurantId, createdAt);
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId='" + restaurantId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
