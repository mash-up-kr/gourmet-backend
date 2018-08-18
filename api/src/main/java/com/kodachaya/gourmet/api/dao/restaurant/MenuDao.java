package com.kodachaya.gourmet.api.dao.restaurant;

import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<MenuEntity, Integer> {
}
