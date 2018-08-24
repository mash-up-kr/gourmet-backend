package com.kodachaya.gourmet.api.controller.review;

import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.controller.mapper.UserMapper;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.user.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
public class ReviewController {

    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/review/{reviewId}", method = RequestMethod.GET)
    public @ResponseBody ReviewModel get(@PathVariable("reviewId") int reviewId) {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);

        ReviewModel review = new ReviewModel();
        review.setId(0);
        review.setAuthor(UserMapper.map(entity));
        review.setStamp(Stamp.LIKE);
        review.setMenuTastes(Arrays.asList(Taste.BITTER, Taste.SALTY, Taste.SPICY, Taste.SWEET));

        RestaurantModel restaurant = new RestaurantModel();
        restaurant.setId(0);
        restaurant.setRegisteredTime(LocalDateTime.now());
        restaurant.setName("코다차야 강남점");
        restaurant.setAddress("서울특별시 강남구 역삼동");
        restaurant.setLatitude(37.5041302);
        restaurant.setLongitude(127.025463);
        review.setRestaurant(restaurant);

        MenuModel menu = new MenuModel();
        menu.setId(0);
        menu.setPrice(12000);
        menu.setName("김치볶음밥");
        menu.setWishedAt(LocalDateTime.now());
        menu.setWished(true);
        menu.setRegisteredTime(LocalDateTime.now());
        review.setMenu(menu);
        return review;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/review/{reviewId}/wish", method = RequestMethod.POST)
    public @ResponseBody void wishReview(@PathVariable("reviewId") int reviewId, HttpServletResponse response) {
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
