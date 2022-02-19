package com.cj.cptrend.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final String PREFIX = "cp/main/";

    @RequestMapping("/")
    public String index() {
        return PREFIX + "index";
    }


    @RequestMapping("/loading")
    public String loading() {
        return "cp/loading/loading";
    }


}
