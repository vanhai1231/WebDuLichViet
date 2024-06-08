package com.hutech.DAMH.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DuLichViet")
public class homeController {
    @GetMapping("/Home")
    public String showProductList(Model model) {
        return "/index/index";
    }
}
