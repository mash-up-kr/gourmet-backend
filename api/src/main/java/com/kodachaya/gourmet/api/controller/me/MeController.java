package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.exception.UnauthorizedException;
import com.kodachaya.gourmet.api.util.Defines;
import com.kodachaya.gourmet.dto.me.MeCommand;
import com.kodachaya.gourmet.dto.user.UserModel;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MeController {

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public @ResponseBody UserModel getMe(@RequestHeader(Defines.HEADER_AUTHORIZATION) Optional<String> bearerToken) {
        String accessToken = bearerToken.filter(s -> s.startsWith(Defines.PREFIX_BEARER))
                .map(s -> s.replace(Defines.PREFIX_BEARER, ""))
                .orElseThrow(UnauthorizedException::new);

        // TODO get user by access token

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("daekyu");
        user.setIntroduce("나는 행복합니다.");
        user.setProfileImage("https://avatars1.githubusercontent.com/u/3222096?s=460&v=4");
        user.setStampCount(100);
        user.setWishCount(1000);
        user.setFollowingCount(300);
        user.setFollowerCount(100);
        user.setIsPublic(true);
        return user;
    }

    @RequestMapping(value = "/api/me", method = RequestMethod.PUT)
    public @ResponseBody UserModel putMe(
            @RequestHeader(Defines.HEADER_AUTHORIZATION) Optional<String> bearerToken,
            @RequestBody MeCommand command) {

        if (command == null || StringUtils.isEmpty(command.getIntroduce()) && StringUtils.isEmpty(command.getProfile())) {
            throw new BadRequestException("Please input the introduce or profile");
        }

        String accessToken = bearerToken.filter(s -> s.startsWith(Defines.PREFIX_BEARER))
                .map(s -> s.replace(Defines.PREFIX_BEARER, ""))
                .orElseThrow(UnauthorizedException::new);
        // TODO get user by access token


        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("daekyu");
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
