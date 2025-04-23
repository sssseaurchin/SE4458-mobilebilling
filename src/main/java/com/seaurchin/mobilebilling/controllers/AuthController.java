package com.seaurchin.mobilebilling.controllers;

import com.seaurchin.mobilebilling.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            return jwtUtil.generateToken(username);
        } else {
            return "Invalid credentials";
        }
    }
}
