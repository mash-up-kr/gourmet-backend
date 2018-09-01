package com.kodachaya.gourmet.api.dao.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuDao extends JpaRepository<MenuEntity, Integer> {

    boolean existsByRestaurantAndName(int restaruantId, String name);


    Optional<MenuEntity> findByRestaurantAndName(int restaurantId, String name);


    List<MenuEntity> findAllByRestaurant(int restaurantId);


    List<MenuEntity> findAllByRestaurantAndNameContaining(int restaurantId, String name);


}
