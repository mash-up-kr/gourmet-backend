package com.kodachaya.gourmet.api.controller.auth;

import com.kodachaya.gourmet.api.dao.UserDao;
import com.kodachaya.gourmet.api.entity.UserEntity;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.dto.auth.TokenModel;
import com.kodachaya.gourmet.dto.auth.command.SignInCommand;
import com.kodachaya.gourmet.dto.auth.command.SignUpCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserDao dao;

    @RequestMapping(value = "/api/auth/register", method = RequestMethod.POST)
    public @ResponseBody TokenModel register(@RequestBody SignUpCommand command) {

        // validate username or password empty
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input right username or password");
        }

        String username = command.getUsername();
        String password = command.getPassword();

        // check already username exists.
        if (dao.findByUsername(username).isPresent()) {
            throw new BadRequestException("Already Exists");
        }

        // save to database
        dao.save(new UserEntity(username, password));

        // oauth2
        TokenModel token = new TokenModel("123123", 123123123, "123123123");
        return token;
    }


    @RequestMapping(value = "/api/auth/login", method = RequestMethod.POST)
    public @ResponseBody TokenModel login(@RequestBody SignInCommand command) {

        // validate username or password empty
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input right username or password");
        }

        String username = command.getUsername();
        String password = command.getPassword();

        Optional<UserEntity> optionalUser = dao.findByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()) {
            TokenModel token = new TokenModel("123123", 123123123, "123123123");
            return token;
        }

        throw new BadRequestException("Please input the right username or password");
    }


}
