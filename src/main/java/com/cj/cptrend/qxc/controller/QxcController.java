package com.cj.cptrend.qxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qxc")
public class QxcController {
    public static final String PREFIX = "cp/qxc/";

    @GetMapping("/qxc")
    public String qxc() {
        return PREFIX + "qxc";
    }
}
