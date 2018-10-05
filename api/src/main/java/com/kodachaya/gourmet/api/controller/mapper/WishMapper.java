package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.wish.WishModel;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;

public class WishMapper {

    public static WishModel map(WishEntity entity) {
        WishModel wish = new WishModel();

        wish.setWishId(entity.getId());
        wish.setWishedAt(entity.getCreatedAt());

        // set menu
        MenuModel menu = MenuMapper.map(entity.getMenu());
        menu.setWished(true);
        menu.setWishedAt(entity.getCreatedAt());
        wish.setMenu(menu);

        // set restaurant
        RestaurantModel restaurant = RestaurantMapper.map(entity.getMenu().getRestaurant());
        wish.setRestaurant(restaurant);

        // set review
        if (entity.getReview() != null) {
            ReviewModel review = ReviewMapper.map(entity.getReview());
            wish.setReview(review);
            wish.setWishImageUrl(review.getReviewImageUrl());
        } else {
            wish.setWishImageUrl(entity.getWishImage());
        }
        return wish;
    }
}
