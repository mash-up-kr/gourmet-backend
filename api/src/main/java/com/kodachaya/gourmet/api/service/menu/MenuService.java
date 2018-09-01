package com.kodachaya.gourmet.api.service.menu;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    boolean exists(int restaurantId, String name);


    MenuEntity create(int restaurantId, String name);


    MenuEntity create(int restaurantId, String name, Integer price);


    MenuEntity update(int menuId, Integer price);


    List<MenuEntity> search(int restaurantId, String name);


    List<MenuEntity> findAll(int restaurantId);

}
