package com.example.springsecdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String home(HttpServletRequest request) {
        return "Home Page " + request.getRequestedSessionId();
    }

    @GetMapping("hello")
    public String hello(HttpServletRequest request) {
        return "Hello World Page " + request.getSession().getId();
    }
}
