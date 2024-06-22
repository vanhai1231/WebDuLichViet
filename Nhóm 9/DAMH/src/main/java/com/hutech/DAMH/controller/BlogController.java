package com.hutech.DAMH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Blog")
public class BlogController {

    @GetMapping
    public String viewBlog() {
        return "Blog"; // This will return the blog.html page
    }

    @GetMapping("/post")
    public String viewPost() {
        return "/index/post"; // This will return the post.html page
    }
}
