package com.kodachaya.gourmet.api.service.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.List;

public interface RestaurantService {


    boolean exists(String name);


    RestaurantEntity create(String name, String address);


    RestaurantEntity create(String name, String address, Double latitude, Double longitude);


    RestaurantEntity update(int restaurantId, String address);


    RestaurantEntity update(int restaurantId, String address, Double latitude, Double longitude);


    List<RestaurantEntity> search(String name);

}
