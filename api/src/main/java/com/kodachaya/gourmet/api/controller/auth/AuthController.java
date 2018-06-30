package com.kodachaya.gourmet.api.controller.auth;

import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.dto.BaseListModel;
import com.kodachaya.gourmet.dto.auth.TokenModel;
import com.kodachaya.gourmet.dto.auth.command.RefreshCommand;
import com.kodachaya.gourmet.dto.auth.command.SignInCommand;
import com.kodachaya.gourmet.dto.auth.command.SignUpCommand;
import com.kodachaya.gourmet.dto.user.UserModel;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @RequestMapping(value = "/api/auth/register", method = RequestMethod.POST)
    public @ResponseBody TokenModel register(@RequestBody SignUpCommand command) {
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input username and password");
        }

        String username = command.getUsername();
        String password = command.getPassword();
        // TODO username / password empty
        Optional<String> emptyName = Optional.ofNullable(username);
        Optional<String> emptyPassword = Optional.ofNullable(password);
        // TODO user name exists ?
        if(emptyName.isPresent()) {
            emptyName.get();
            emptyPassword.get();
        }
        // TODO username / password -> insert

        TokenModel token = new TokenModel("123123", 123123123, "123123123");
        return token;

    }

    @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public @ResponseBody TokenModel login(@RequestBody SignInCommand command) {
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input username and password");
        }

        String username = command.getUsername();
        String password = command.getPassword();

        TokenModel token = new TokenModel("123123", 123123123, "123123123");
        return token;
    }

    @RequestMapping(value = "/api/auth/refresh", method = RequestMethod.POST)
    public @ResponseBody TokenModel refresh(@RequestBody RefreshCommand command) {
        if (command == null || StringUtils.isEmpty(command.getRefreshToken())) {
            throw new BadRequestException("Please input refreshToken");
        }
        String refreshToken = command.getRefreshToken();

        TokenModel token = new TokenModel("123123", 123123123, "123123123");
        return token;
    }

}
