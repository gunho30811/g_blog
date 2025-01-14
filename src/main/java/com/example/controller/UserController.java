package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping(value = "/user", produces = "application/json; charset=UTF-8")
    public String getUser(int id) {
        return "{\"message\": \"Number 1\"}";
}
}
*/

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping(value = "/user", produces = "application/json; charset=UTF-8")
    public String getUser(@RequestParam(defaultValue = "1") int id) {
        System.out.println("API Called with ID: " + id); // 요청 확인
        if (id == 1) {
            return "{\"message\": \"Numbe라r 1\"}";
        } else if (id == 2) {
            return "{\"message\": \"Number 2\"}";
        } else {
            return "{\"message\": \"Unknown Number\"}";
        }
    }
}
