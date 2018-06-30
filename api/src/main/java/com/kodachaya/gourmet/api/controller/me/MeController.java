package com.kodachaya.gourmet.api.controller.me;

import com.kodachaya.gourmet.dto.me.MeCommand;
import com.kodachaya.gourmet.dto.user.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MeController {

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public @ResponseBody UserModel getMe(@RequestHeader("Authorization") Optional<String> bearerToken) {
        UserModel user = new UserModel(
                1,
                "daekyu",
                "https://avatars1.githubusercontent.com/u/3222096?s=460&v=4",
                100,
                1000,
                112,
                100,
                false,
                true
        );
        return user;
    }

    @RequestMapping(value = "/api/me", method = RequestMethod.PUT)
    public @ResponseBody UserModel putMe(@RequestHeader("Authorization") Optional<String> bearerToken, @RequestBody MeCommand command) {
        UserModel user = new UserModel(
                1,
                "daekyu",
                "https://avatars1.githubusercontent.com/u/3222096?s=460&v=4",
                100,
                1000,
                112,
                100,
                false,
                true
        );
        return user;
    }

}
