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
import com.kodachaya.gourmet.api.service.storage.StorageService;
import com.kodachaya.gourmet.api.service.user.UserService;
import com.kodachaya.gourmet.api.service.wish.WishService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.OptionalInt;

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


    @Autowired
    private StorageService storageService;



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ReviewModel.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/wish", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody WishModel create(@RequestPart(value = "image") MultipartFile image,
                                          @RequestPart(value = "restaurant") String restaurant,
                                          @RequestPart(value = "address") String address,
                                          @RequestPart(value = "menu") String menu,
                                          @RequestPart(value = "price", required = false) String price) throws FileNotFoundException {


        String wishImageUrl = storageService.uploadWishImage(image);

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity user = userService.get(username).orElseThrow(UnauthorizedException::new);


        RestaurantEntity restaurantEntity = restaurantService.find(restaurant)
                .orElseGet(() -> restaurantService.create(restaurant, address));

        MenuEntity menuEntity = menuService.find(restaurantEntity, menu)
                .orElseGet(() -> menuService.create(restaurantEntity, menu, price == null ? 0 : Integer.parseInt(price)));

        WishEntity wish = wishService.makeWish(user.getId(), menuEntity, wishImageUrl);
        return WishMapper.map(wish);
    }


}
