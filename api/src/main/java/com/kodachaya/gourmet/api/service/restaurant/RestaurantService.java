package com.kodachaya.gourmet.api.service.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.Optional;

public interface RestaurantService {

    Optional<RestaurantEntity> create(String name);


    Optional<RestaurantEntity> create(String name, String address);


    Optional<RestaurantEntity> create(String name, String address, Double latitude, Double longitude);


    Optional<RestaurantEntity> update(int restaurantId, String address);


    Optional<RestaurantEntity> update(int restaurantId, String address, Double latitude, Double longitude);


    Optional<RestaurantEntity> find(String query);

}
