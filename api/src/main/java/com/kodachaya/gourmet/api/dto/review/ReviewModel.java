package com.kodachaya.gourmet.api.dto.review;

import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.user.UserModel;

import java.util.List;
import java.util.Objects;

public class ReviewModel {

    private int id;
    private UserModel author;
    private Stamp stamp;
    private RestaurantModel restaurant;
    private MenuModel menu;
    private List<Taste> menuTastes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
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

    public List<Taste> getMenuTastes() {
        return menuTastes;
    }

    public void setMenuTastes(List<Taste> menuTastes) {
        this.menuTastes = menuTastes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewModel that = (ReviewModel) o;
        return id == that.id &&
                Objects.equals(author, that.author) &&
                stamp == that.stamp &&
                Objects.equals(restaurant, that.restaurant) &&
                Objects.equals(menu, that.menu) &&
                Objects.equals(menuTastes, that.menuTastes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, author, stamp, restaurant, menu, menuTastes);
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "id=" + id +
                ", author=" + author +
                ", stamp=" + stamp +
                ", restaurant=" + restaurant +
                ", menu=" + menu +
                ", menuTastes=" + menuTastes +
                '}';
    }
}
