package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.TinTuc;
import com.hutech.DAMH.repository.TinTucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private TinTucRepository tinTucRepository;

    @GetMapping("/DuLichViet/Blog")
    public String getAllPosts(Model model) {
        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        List<TinTuc> tinTucList = tinTucRepository.findAll();
        model.addAttribute("tinTucList", tinTucList);
        return "index/blog";
    }

    @GetMapping("/blog")
    public String getBlog(Model model) {
        List<TinTuc> tinTucList = tinTucRepository.findAll();
        model.addAttribute("tinTucList", tinTucList);
        return "index/blog";
    }

    @GetMapping("/blog/{id}")
    public String getBlogPost(@PathVariable("id") Long id, Model model) {
        Optional<TinTuc> tinTucOptional = tinTucRepository.findById(id);
        if (tinTucOptional.isPresent()) {
            model.addAttribute("tinTuc", tinTucOptional.get());
            return "post";
        } else {
            return "404"; // or handle the error as appropriate
        }
    }
}
