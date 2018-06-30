package com.kodachaya.gourmet.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReviewModel {

    private int id;
    private Stamp LIKE;
    private UserModel author;
    private RestaurantModel restaurant;
    private MenuModel menu;

    public ReviewModel(int id, Stamp LIKE, UserModel author, RestaurantModel restaurant, MenuModel menu) {
        this.id = id;
        this.LIKE = LIKE;
        this.author = author;
        this.restaurant = restaurant;
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stamp getLIKE() {
        return LIKE;
    }

    public void setLIKE(Stamp LIKE) {
        this.LIKE = LIKE;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewModel that = (ReviewModel) o;
        return id == that.id &&
                LIKE == that.LIKE &&
                Objects.equals(author, that.author) &&
                Objects.equals(restaurant, that.restaurant) &&
                Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, LIKE, author, restaurant, menu);
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "id=" + id +
                ", LIKE=" + LIKE +
                ", author=" + author +
                ", restaurant=" + restaurant +
                ", menu=" + menu +
                '}';
    }
}
