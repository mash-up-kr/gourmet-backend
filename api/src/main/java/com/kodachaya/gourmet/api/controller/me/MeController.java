package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.api.dto.me.MeCommand;
import com.kodachaya.gourmet.api.dto.user.UserModel;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.service.CustomUserDetails;
import com.kodachaya.gourmet.api.service.UserService;
import com.kodachaya.gourmet.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class MeController {

    @Autowired
    private UserService userService;

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

}
