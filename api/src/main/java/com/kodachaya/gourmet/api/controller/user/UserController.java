package com.kodachaya.gourmet.api.controller.user;

import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.controller.mapper.UserMapper;
import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.NotFoundException;
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
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public @ResponseBody UserModel get(@PathVariable("userId") int userId) {
        UserEntity entity = userService.find(userId).orElseThrow(() -> new NotFoundException("Not found by userId"));
        UserModel user = UserMapper.map(entity);
        user.setIsFollowing(new Random().nextInt() % 2 == 0);
        user.setStampCount(100);
        user.setWishCount(100);
        user.setIntroduce("나는 행복합니다.");
        return user;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/{userId}/follow", method = RequestMethod.POST)
    public @ResponseBody void follow(@PathVariable("userId") int userId, HttpServletResponse response) {
        UserEntity entity = userService.find(userId).orElseThrow(() -> new NotFoundException("Not found by userId"));
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }


    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/{userId}/follow", method = RequestMethod.DELETE)
    public @ResponseBody void unfollow(@PathVariable("userId") int userId, HttpServletResponse response) {
        UserEntity entity = userService.find(userId).orElseThrow(() -> new NotFoundException("Not found by userId"));
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/{userId}/stamps", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<ReviewModel> getStamps(
            @PathVariable("userId") int userId,
            @RequestParam(value = "count", required = false, defaultValue = "15") Optional<Integer> optionalCount,
            @RequestParam(value = "cursor", required = false) Optional<Integer> optionalCursor) {
        UserEntity entity = userService.find(userId).orElseThrow(() -> new NotFoundException("Not found by userId"));
        UserModel user = UserMapper.map(entity);
        user.setIsFollowing(new Random().nextInt() % 2 == 0);
        user.setStampCount(100);
        user.setWishCount(100);
        user.setIntroduce("나는 행복합니다.");

        BaseListModel<ReviewModel> response = new BaseListModel<>();
        response.setTotalCount(100);
        response.setCount(15);
        response.setCursor(16);

        List<ReviewModel> reviews = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            ReviewModel review = new ReviewModel();
            review.setId(0);
            review.setAuthor(user);
            review.setStamp(i % 2 == 0 ? Stamp.LIKE : Stamp.DISLIKE);
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

            reviews.add(review);
        }
        response.setData(reviews);

        return response;
    }
}
