package com.kodachaya.gourmet.api.controller.wish;

import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.controller.mapper.WishMapper;
import com.kodachaya.gourmet.api.dto.menu.command.MenuPostCommand;
import com.kodachaya.gourmet.api.dto.restaurant.command.RestaurantPostCommand;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.wish.WishModel;
import com.kodachaya.gourmet.api.dto.wish.command.WishPostCommand;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.entity.wish.WishEntity;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.menu.MenuService;
import com.kodachaya.gourmet.api.service.restaurant.RestaurantService;
import com.kodachaya.gourmet.api.service.user.UserService;
import com.kodachaya.gourmet.api.service.wish.WishService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishController {

    @Autowired
    private UserService userService;


    @Autowired
    private WishService wishService;


    @Autowired
    private MenuService menuService;


    @Autowired
    private RestaurantService restaurantService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ReviewModel.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/wish", method = RequestMethod.POST)
    public @ResponseBody WishModel create(@RequestBody WishPostCommand command) {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity user = userService.get(username).orElseThrow(UnauthorizedException::new);

        RestaurantPostCommand restaurantCommand = command.getRestaurant();
        MenuPostCommand menuCommand = command.getMenu();

        //TODO upload menu pictures and get urls

        RestaurantEntity restaurant = restaurantService.find(restaurantCommand.getName())
                .orElseGet(() -> restaurantService.create(restaurantCommand.getName(), restaurantCommand.getAddress()));

        MenuEntity menu = menuService.find(restaurant, menuCommand.getName())
                .orElseGet(() -> menuService.create(restaurant, menuCommand.getName(), menuCommand.getPrice().get()));

        WishEntity wish = wishService.makeWish(user.getId(), menu);
        return WishMapper.map(wish);
    }

}
