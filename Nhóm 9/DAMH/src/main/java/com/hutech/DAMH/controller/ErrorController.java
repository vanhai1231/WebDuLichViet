package com.hutech.DAMH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController {
    @GetMapping("LoiXoaTaiKhoan")
    public String showFormError(){
        return "/LoiXoaTaiKhoan";
    }
    @GetMapping("LoiXoaKhachSan")
    public String showFormErrorKS(){
        return "/LoiXoaKhachSan";
    }
    @GetMapping("LoiXoaLoaiTour")
    public String showFormErrorLoaiTour(){
        return "/LoiXoaLoaiTour";
    }
    @GetMapping("LoiXoaPhong")
    public String showFormErrorPhong(){
        return "/LoiXoaPhong";
    }
    @GetMapping("LoiXoaLoaiPhong")
    public String showFormErrorLPhong(){
        return "/LoiXoaLoaiPhong";
    }
    @GetMapping("LoiXoaDiemDen")
    public String showFormErrorDD(){
        return "/LoiXoaDiemDen";
    }
}
