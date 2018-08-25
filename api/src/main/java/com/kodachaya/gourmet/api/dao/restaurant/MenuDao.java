package com.kodachaya.gourmet.api.dao.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuDao extends JpaRepository<MenuEntity, Integer> {

    Optional<MenuEntity> findByName(String name);
}
