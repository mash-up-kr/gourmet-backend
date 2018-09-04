package com.kodachaya.gourmet.api.service.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {


    boolean exists(String name);


    RestaurantEntity create(String name, String address);


    RestaurantEntity create(String name, String address, Double latitude, Double longitude);


    RestaurantEntity update(int restaurantId, String address);


    RestaurantEntity update(int restaurantId, String address, Double latitude, Double longitude);


    Optional<RestaurantEntity> find(String name);


    List<RestaurantEntity> search(String name);

}
