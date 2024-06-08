package com.hutech.DAMH.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    // Display a list of all products
    @GetMapping(value= "/login")
    public String showProductList() {
        return "Login";
    }
}
