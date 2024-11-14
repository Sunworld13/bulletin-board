package com.example.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappController {
    @GetMapping("/admin")
    public String adminPage() {
        return "ADMIN";
    }

}