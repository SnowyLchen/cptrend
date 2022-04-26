package com.cj.cptrend.p5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/p5")
public class P5Controller {
    public static final String PREFIX = "cp/p5/";

    @GetMapping("/p5")
    public String p5() {
        return PREFIX + "p5";
    }
}
