package com.kodachaya.gourmet.api.controller.mapper;

import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;

public class MenuMapper {

    public static MenuModel map(MenuEntity entity) {
        MenuModel menu = new MenuModel();
        menu.setId(entity.getId());
        menu.setName(entity.getName());
        menu.setPrice(entity.getPrice());
        menu.setRegisteredTime(entity.getCreatedAt());
        return menu;
    }
}
