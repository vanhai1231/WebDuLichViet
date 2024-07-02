package com.hutech.DAMH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReturnStatusController {

    @GetMapping("/payment/success")
    public String showSuccessPage() {
        return "index/ThanhToanThanhCong"; // Return the Thymeleaf template for success
    }

    @GetMapping("/payment/failure")
    public String showFailurePage() {
        return "index/ThanhToanThatBai"; // Return the Thymeleaf template for failure
    }
}
