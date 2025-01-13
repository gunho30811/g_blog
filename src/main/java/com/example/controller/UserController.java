package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping(value = "/user", produces = "text/plain; charset=UTF-8")
    public String getUser(@RequestParam(defaultValue = "0") int id) {
        if (id == 1) {
            return "Number 1";
        } else if (id == 2) {
            return "Number 2";
        } else {
            return "Unknown Number";
        }
    }
}
