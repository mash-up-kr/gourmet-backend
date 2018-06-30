package com.kodachaya.gourmet.api.controller.auth;

import com.kodachaya.gourmet.dto.auth.TokenModel;
import com.kodachaya.gourmet.dto.auth.command.SignUpCommand;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @RequestMapping(value = "/api/auth/register", method = RequestMethod.POST)
    public @ResponseBody TokenModel register(@RequestBody SignUpCommand command) {
        String username = command.getUsername();
        String password = command.getPassword();
        // TODO username / password empty
        // TODO user name exists ?
        // TODO username / password -> insert
        TokenModel token = new TokenModel("123123", 123123123, "123123123");
        return token;
    }
}
