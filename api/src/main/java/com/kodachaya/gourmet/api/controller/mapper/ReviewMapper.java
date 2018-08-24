package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {

    public static ReviewModel map(ReviewEntity entity) {
        ReviewModel review = new ReviewModel();
        review.setId(entity.getId());

        // set author
        UserModel author = UserMapper.map(entity.getAuthor());
        review.setAuthor(author);

        // set stamp
        review.setStamp(entity.getStamp().getExpression());

        // set tastes
        List<Taste> tastes = new ArrayList<>();
        entity.getTastes().stream().forEach(tasteEntity -> tastes.add(tasteEntity.getTaste().getTaste()));
        review.setMenuTastes(tastes);

        // set menu
        review.setMenu(MenuMapper.map(entity.getMenu()));

        // set restaurant
        review.setRestaurant(RestaurantMapper.map(entity.getMenu().getRestaurant()));

        return review;
    }

}
