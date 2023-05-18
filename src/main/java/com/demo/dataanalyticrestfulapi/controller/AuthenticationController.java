package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @PostMapping("/register")
    public Response<?> register(){
        return null;
    }

    @GetMapping("/login")
    public Response<?> login(){
        return null;
    }

}
