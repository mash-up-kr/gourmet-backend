package com.kodachaya.gourmet.api.controller.auth;

import com.kodachaya.gourmet.api.dto.auth.command.SignUpCommand;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public void register(@RequestBody SignUpCommand command, HttpServletResponse response) {

        // validate username or password empty
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input right username or password");
        }

        userService.save(command.getUsername(), command.getPassword());
        response.setStatus(HttpStatus.CREATED.value());
    }



}
