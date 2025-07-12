package com.levelup.erp.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // loads login.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "auth/dashboard"; // loads dashboard.html after login
    }
}
