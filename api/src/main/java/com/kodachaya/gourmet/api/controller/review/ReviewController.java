package com.kodachaya.gourmet.api.controller.review;

import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.controller.mapper.ReviewMapper;
import com.kodachaya.gourmet.api.controller.mapper.WishMapper;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.command.MenuPostCommand;
import com.kodachaya.gourmet.api.dto.restaurant.command.RestaurantPostCommand;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.review.command.ReviewPostCommand;
import com.kodachaya.gourmet.api.dto.wish.WishModel;
import com.kodachaya.gourmet.api.entity.restaurant.MenuEntity;
import com.kodachaya.gourmet.api.entity.restaurant.RestaurantEntity;
import com.kodachaya.gourmet.api.entity.review.ReviewEntity;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.NotFoundException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.menu.MenuService;
import com.kodachaya.gourmet.api.service.restaurant.RestaurantService;
import com.kodachaya.gourmet.api.service.review.ReviewService;
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
import java.util.Arrays;
import java.util.Optional;

@RestController
public class ReviewController {

    @Autowired
    private UserService userService;


    @Autowired
    private ReviewService reviewService;


    @Autowired
    private WishService wishService;


    @Autowired
    private MenuService menuService;


    @Autowired
    private RestaurantService restaurantService;


    @Autowired
    private StorageService storageService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.GET)
    public @ResponseBody ReviewModel get(@PathVariable("reviewId") int reviewId) {
        Optional<ReviewEntity> reviewEntity = reviewService.getReview(reviewId);
        return ReviewMapper.map(reviewEntity.orElseThrow(() -> new NotFoundException("Review not found")));
    }


    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/review/{reviewId}/wish", method = RequestMethod.POST)
    public @ResponseBody WishModel wishReview(@PathVariable("reviewId") int reviewId) {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);
        return WishMapper.map(wishService.makeWish(entity.getId(), reviewId));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ReviewModel.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/review", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ReviewModel create(@RequestPart(value = "image") MultipartFile image,
                                          @RequestPart(value = "restaurant") String restaurant,
                                          @RequestPart(value = "address") String address,
                                          @RequestPart(value = "menu") String menu,
                                          @RequestPart(value = "price", required = false) Optional<Integer> price,
                                          @RequestPart(value = "comment") String comment,
                                          @RequestPart(value = "stamp") String stamp) throws FileNotFoundException {


        String reviewImageUrl = storageService.uploadReviewImage(image);

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity user = userService.get(username).orElseThrow(UnauthorizedException::new);


        RestaurantEntity restaurantEntity = restaurantService.find(restaurant)
                .orElseGet(() -> restaurantService.create(restaurant, address));

        MenuEntity menuEntity = menuService.find(restaurantEntity, menu)
                .orElseGet(() -> menuService.create(restaurantEntity, menu, price.orElse(0)));

        ReviewEntity review = reviewService.create(user.getId(), menuEntity.getId(), Arrays.asList(), comment, Stamp.valueOf(stamp), reviewImageUrl);
        return ReviewMapper.map(review);

    }



}
