package com.kodachaya.gourmet.api.dao.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDao extends JpaRepository<MenuEntity, Integer> {

    boolean existsByRestaurantAndName(RestaurantEntity restaurant, String name);


    Optional<MenuEntity> findByRestaurantAndName(RestaurantEntity restaurant, String name);


    List<MenuEntity> findAllByRestaurant(RestaurantEntity restaurant);


    List<MenuEntity> findAllByRestaurantAndNameContaining(RestaurantEntity restaurant, String name);


}
