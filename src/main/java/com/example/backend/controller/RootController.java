package com.example.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

    public RootController(){
        System.out.println("RootController created");
    }

    @GetMapping("/")
//    @ResponseBody
    public String root(){
        return "index";
    }
}
