package com.kodachaya.gourmet.api.controller.auth;

import com.kodachaya.gourmet.api.dto.auth.command.SignUpCommand;
import com.kodachaya.gourmet.api.exception.AlreadyExistsException;
import com.kodachaya.gourmet.api.exception.BadRequestException;
import com.kodachaya.gourmet.api.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 409, message = "Already Exists"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST, produces = "application/json")
    public void register(@RequestBody SignUpCommand command, HttpServletResponse response) {

        // validate username or password empty
        if (command == null || StringUtils.isEmpty(command.getUsername()) || StringUtils.isEmpty(command.getPassword())) {
            throw new BadRequestException("Please input right username or password");
        }

        userService.get(command.getUsername()).ifPresent((item) -> {
            throw new AlreadyExistsException();
        });

        userService.save(command.getUsername(), command.getPassword());
        response.setStatus(HttpStatus.CREATED.value());
    }



}
