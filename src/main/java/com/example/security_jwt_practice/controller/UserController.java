package com.example.security_jwt_practice.controller;

import com.example.security_jwt_practice.dto.LoginRequestDto;
import com.example.security_jwt_practice.dto.UserDto;
import com.example.security_jwt_practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public UserDto login(@RequestBody LoginRequestDto loginRequestDto) throws Exception{
        return userService.login(loginRequestDto);
    }

    @GetMapping(value = "/customer/get")
    public String customer() throws Exception{
        return "customer";
    }
}
