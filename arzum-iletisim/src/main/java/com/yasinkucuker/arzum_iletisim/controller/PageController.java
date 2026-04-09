package com.yasinkucuker.arzum_iletisim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/dashboard/index")
    public String dashboardPage() {
        return "dashboard/index";
    }
}
