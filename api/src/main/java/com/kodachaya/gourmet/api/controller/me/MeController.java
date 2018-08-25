package com.kodachaya.gourmet.api.controller.me;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.me.MeCommand;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.controller.mapper.UserMapper;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.dto.wish.WishModel;
import com.kodachaya.gourmet.api.entity.user.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.config.CustomUserDetails;
import com.kodachaya.gourmet.api.service.user.UserService;
import com.kodachaya.gourmet.api.service.wish.WishService;
import com.kodachaya.gourmet.api.service.storage.StorageService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MeController {


    private final StorageService storageService;

    public MeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private UserService userService;


    @Autowired
    private WishService wishService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserModel.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody UserModel getMe() {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);

        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(username);
        user.setIsPublic(entity.isPublic());
        user.setIntroduce(entity.getIntroduce());
        user.setProfileImage(entity.getProfile());
        user.setFollowerCount(entity.getFollowers().size());
        user.setFollowingCount(entity.getFollowings().size());
        user.setStampCount(0);
        user.setWishCount(0);
        user.setProfileImage(null);
        return user;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserModel.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me", method = RequestMethod.PUT)
    public void putMe(MeCommand command, HttpServletResponse response) throws FileNotFoundException {

        if (command == null || StringUtils.isEmpty(command.getIntroduce()) && StringUtils.isEmpty(command.getProfile())) {
            throw new BadRequestException("Please input the introduce or profile");
        }

        // upload profile to storage
        String profileImage = storageService.uploadProfile(command.getProfile());
        String introduce = command.getIntroduce();

        // put your code by using user service

        response.setStatus(HttpStatus.ACCEPTED.value());
    }




    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me/wishlist", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<WishModel> getWishes(@RequestParam(value = "count", required = false, defaultValue = "15") Optional<Integer> optionalCount,
                                                            @RequestParam(value = "cursor", required = false) Optional<Integer> optionalCursor) {

//        int count = optionalCount.filter(c -> c > 0).orElse(15);
//        int cursor = optionalCursor.filter(c -> c > 0).orElse(0);
//
//        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//        UserEntity userEntity = userService.get(username).orElseThrow(UnauthorizedException::new);
//        int wishCount = wishService.getWishCount();
//
//        List<WishEntity> wishEntities = wishService.getWishes(userEntity.getId(), cursor, count + 1);
//        if (wishEntities.size() == 0) {
//            throw new NotFoundException("Please wish first");
//        }
//
//        int wishEntitiesSize = wishEntities.size();
//        int sublistLastIndex = count < wishEntitiesSize ? count - 1 : wishEntitiesSize - 1;
//        List<WishModel> wishlist = wishEntities.subList(0, sublistLastIndex)
//                .stream()
//                .map(WishMapper::map)
//                .collect(Collectors.toList());
//
//        BaseListModel<WishModel> response = new BaseListModel<>();
//        response.setData(wishlist);
//        response.setTotalCount(wishCount);
//        response.setCount(wishlist.size());
//        response.setCursor(count < wishEntitiesSize ? wishEntities.get(wishEntitiesSize - 1).getId() : null);
        BaseListModel<WishModel> response = new BaseListModel<>();
        response.setTotalCount(100);
        response.setCount(15);
        response.setCursor(16);

        List<WishModel> wishlist = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            WishModel wish = new WishModel();
            wish.setWishedAt(LocalDateTime.now());
            wish.setWishId(i);

            RestaurantModel restaurant = new RestaurantModel();
            restaurant.setId(0);
            restaurant.setRegisteredTime(LocalDateTime.now());
            restaurant.setName("코다차야 강남점");
            restaurant.setAddress("서울특별시 강남구 역삼동");
            restaurant.setLatitude(37.5041302);
            restaurant.setLongitude(127.025463);
            wish.setRestaurant(restaurant);

            MenuModel menu = new MenuModel();
            menu.setId(0);
            menu.setPrice(12000);
            menu.setName("김치볶음밥");
            menu.setWishedAt(LocalDateTime.now());
            menu.setWished(true);
            menu.setRegisteredTime(LocalDateTime.now());
            wish.setMenu(menu);


            if (i % 2 == 0) {
                ReviewModel review = new ReviewModel();
                review.setId(0);
                review.setStamp(Stamp.LIKE);
                review.setAuthor(UserMapper.map(userService.get("bigstark").get()));
                review.setMenuTastes(Arrays.asList(Taste.BITTER, Taste.SALTY, Taste.SPICY));
                wish.setReview(review);
            }
            wishlist.add(wish);
        }
        response.setData(wishlist);

        return response;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/me/stamps", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<ReviewModel> getStamps(@RequestParam(value = "count", required = false, defaultValue = "15") Optional<Integer> optionalCount,
                                                            @RequestParam(value = "cursor", required = false) Optional<Integer> optionalCursor) {

        BaseListModel<ReviewModel> response = new BaseListModel<>();
        response.setTotalCount(100);
        response.setCount(15);
        response.setCursor(16);

        List<ReviewModel> reviews = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            ReviewModel review = new ReviewModel();
            review.setId(0);
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
