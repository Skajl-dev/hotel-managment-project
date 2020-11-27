package com.softserve.greencity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    @GetMapping("/")
    public String test() {
        return "welcome";
    }


    //comment
}
