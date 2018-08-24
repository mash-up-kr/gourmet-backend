package com.kodachaya.gourmet.api.dto.wish;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishModel {

    private int wishId;

    private MenuModel menu;

    private RestaurantModel restaurant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime wishedAt;

    private ReviewModel review;

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getWishedAt() {
        return wishedAt;
    }

    public void setWishedAt(LocalDateTime wishedAt) {
        this.wishedAt = wishedAt;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishModel wishModel = (WishModel) o;
        return wishId == wishModel.wishId &&
                Objects.equals(menu, wishModel.menu) &&
                Objects.equals(restaurant, wishModel.restaurant) &&
                Objects.equals(wishedAt, wishModel.wishedAt) &&
                Objects.equals(review, wishModel.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishId, menu, restaurant, wishedAt, review);
    }

    @Override
    public String toString() {
        return "WishModel{" +
                "wishId=" + wishId +
                ", menu=" + menu +
                ", restaurant=" + restaurant +
                ", wishedAt=" + wishedAt +
                ", review=" + review +
                '}';
    }
}
