package com.cj.cptrend.p3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/p3")
public class P3Controller {
    public static final String PREFIX = "cp/p3/";

    @GetMapping("/p3")
    public String p3() {
        return PREFIX + "p3";
    }
}
