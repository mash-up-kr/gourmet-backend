package com.kodachaya.gourmet.api.service.menu;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    boolean exists(RestaurantEntity restaurant, String name);


    MenuEntity create(RestaurantEntity restaurant, String name);


    MenuEntity create(RestaurantEntity restaurant, String name, Integer price);


    MenuEntity update(int menuId, Integer price);


    List<MenuEntity> search(RestaurantEntity restaurant, String name);


    List<MenuEntity> findAll(RestaurantEntity restaurant);


    Optional<MenuEntity> find(RestaurantEntity restaurant, String name);

}
