package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.api.dto.BaseListModel;
import com.kodachaya.gourmet.api.dto.me.MeCommand;
import com.kodachaya.gourmet.api.dto.review.ReviewModel;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.entity.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.CustomUserDetails;
import com.kodachaya.gourmet.api.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public void putMe(@RequestBody MeCommand command, HttpServletResponse response) {

        if (command == null || StringUtils.isEmpty(command.getIntroduce()) && StringUtils.isEmpty(command.getProfile())) {
            throw new BadRequestException("Please input the introduce or profile");
        }

        // upload profile to storage
        // put your code by using user service

        response.setStatus(HttpStatus.ACCEPTED.value());
    }


   

}
