package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.Stamp;
import com.kodachaya.gourmet.api.dto.me.MeCommand;
import com.kodachaya.gourmet.api.dto.menu.MenuModel;
import com.kodachaya.gourmet.api.dto.restaurant.RestaurantModel;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.review.Taste;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.CustomUserDetails;
import com.kodachaya.gourmet.api.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MeController {

    @Autowired
    private UserService userService;

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
    public @ResponseBody UserModel putMe(@RequestBody MeCommand command) {

        if (command == null || StringUtils.isEmpty(command.getIntroduce()) && StringUtils.isEmpty(command.getProfile())) {
            throw new BadRequestException("Please input the introduce or profile");
        }

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserEntity entity = userService.get(username).orElseThrow(UnauthorizedException::new);


        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setIntroduce("나는 행복합니다.");
        user.setProfileImage("https://avatars1.githubusercontent.com/u/3222096?s=460&v=4");
        user.setStampCount(100);
        user.setWishCount(1000);
        user.setFollowingCount(300);
        user.setFollowerCount(100);
        user.setIsPublic(true);


        // TODO update profile or image
        return user;
    }


    // swagger setting
    @RequestMapping(value = "/me/stamps", method = RequestMethod.GET)
    public @ResponseBody BaseListModel<ReviewModel> getStamps(
            @RequestParam("count") Optional<Integer> optionalCount,
            @RequestParam("cursor") Optional<Integer> cursor) {

        // return dummy data
                UserModel user = new UserModel();
        Stamp stamp = Stamp.DISLIKE;
        RestaurantModel restaurant = new RestaurantModel(1, "jine,", 128, 111, LocalDateTime.now());
        MenuModel menu = new MenuModel(2, "chocolate", 10000, true, null, LocalDateTime.now());
        List<Taste> taste = new ArrayList<>();


        user.setId(2);
        user.setUsername("testUser");
        user.setIntroduce("이수언니 초콜릿 먹고싶음");
        user.setProfileImage("https://s3.ap-northeast-2.amazonaws.com/mf-fileserver/Images/PROFILE/1804170044479124.jpg");
        user.setStampCount(100);
        user.setWishCount(1000);
        user.setFollowingCount(300);
        user.setFollowerCount(100);
        user.setIsPublic(true);

        taste.add(Taste.SWEET);


        ReviewModel r1 = new ReviewModel(1,user,stamp,restaurant,menu,taste);
        ReviewModel r2 = new ReviewModel(2,user,stamp,restaurant,menu,taste);
        ReviewModel r3 = new ReviewModel(3,user,stamp,restaurant,menu,taste);

        BaseListModel<ReviewModel> baselist = new BaseListModel<>();
        List<ReviewModel> testList = new ArrayList<>();
        testList.add(r1);
        testList.add(r2);
        testList.add(r3);

        baselist.setData(testList);

        return baselist;

    }

}
