package com.kodachaya.gourmet.api.dto.review.command;

import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.command.MenuPostCommand;
import com.kodachaya.gourmet.api.dto.restaurant.command.RestaurantPostCommand;

public class ReviewPostCommand {

    private RestaurantPostCommand restaurant;
    private MenuPostCommand menu;
    private String command;
    private Stamp stamp;
    private Integer wishId;

    public RestaurantPostCommand getRestaurant() {
        return restaurant;
    }

    public MenuPostCommand getMenu() {
        return menu;
    }

    public String getCommand() {
        return command;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public Integer getWishId() {
        return wishId;
    }

    @Override
    public String toString() {
        return "ReviewPostCommand{" +
                "restaurant=" + restaurant +
                ", menu=" + menu +
                ", command='" + command + '\'' +
                ", stamp=" + stamp +
                ", wishId=" + wishId +
                '}';
    }
}
