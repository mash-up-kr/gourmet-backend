package com.kodachaya.gourmet.api.dto.wish.command;

import com.kodachaya.gourmet.api.dto.menu.command.MenuPostCommand;
import com.kodachaya.gourmet.api.dto.restaurant.command.RestaurantPostCommand;

public class WishPostCommand {

    private RestaurantPostCommand restaurant;
    private MenuPostCommand menu;

    public RestaurantPostCommand getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantPostCommand restaurant) {
        this.restaurant = restaurant;
    }

    public MenuPostCommand getMenu() {
        return menu;
    }

    public void setMenu(MenuPostCommand menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "WishPostCommand{" +
                "restaurant=" + restaurant +
                ", menu=" + menu +
                '}';
    }
}
