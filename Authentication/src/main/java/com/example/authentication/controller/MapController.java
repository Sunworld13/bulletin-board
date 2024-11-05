package com.example.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MapController {

    @GetMapping("/admin")
    public String adminData() {
        return "ADMIN";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "SECURED";
    }

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "UNSECURED";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        if (principal != null) {
            return principal.getName();
        }
        else {
            return "USER ISN'T AUTHENTICATED";
        }
    }

}