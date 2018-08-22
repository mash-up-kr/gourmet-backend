package com.kodachaya.gourmet.api.service.menu;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Optional<MenuEntity> create(int restaurantId, String name);


    Optional<MenuEntity> create(int restaurantId, String name, int price);


    Optional<MenuEntity> update(int menuId, int price);


    Optional<MenuEntity> find(int restaurantId, String query);


    List<MenuEntity> findAll(int restaurantId);

}
