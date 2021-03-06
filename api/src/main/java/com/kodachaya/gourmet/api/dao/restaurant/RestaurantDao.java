package com.kodachaya.gourmet.api.dao.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantDao extends JpaRepository<RestaurantEntity, Integer> {

    Optional<RestaurantEntity> findByName(String name);


    boolean existsByName(String name);


    List<RestaurantEntity> findByNameContaining(String name);
}
