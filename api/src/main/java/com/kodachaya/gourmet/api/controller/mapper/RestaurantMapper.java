package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

public class RestaurantMapper {

    public static RestaurantModel map(RestaurantEntity entity) {
        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(entity.getId());
        restaurant.setName(entity.getName());
        restaurant.setLatitude(entity.getLatitude());
        restaurant.setLongitude(entity.getLongitude());
        restaurant.setAddress(entity.getAddress());
        restaurant.setRegisteredTime(entity.getCreatedAt());
        return restaurant;
    }

}
