package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.TinTuc;
import com.hutech.DAMH.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Blog")
public class BlogController {

    private final TinTucService tinTucService;

    @Autowired
    public BlogController(TinTucService tinTucService) {
        this.tinTucService = tinTucService;
    }

    @GetMapping
    public String viewBlogList(Model model) {
        List<TinTuc> posts = tinTucService.getAllBlogs();
        model.addAttribute("posts", posts);
        return "/index/Blog";
    }

    @GetMapping("/post/{id}")
    public String viewPost(Model model, @PathVariable("id") int id) {
        Optional<TinTuc> postOptional = tinTucService.getBlogById(id);
        if (postOptional.isPresent()) {
            TinTuc post = postOptional.get();
            model.addAttribute("post", post);
            return "blog-detail"; // Trả về tên của file HTML hiển thị chi tiết bài đăng
        } else {
            // Xử lý trường hợp không tìm thấy bài đăng
            return "error"; // Trả về trang lỗi hoặc xử lý khác tùy theo yêu cầu
        }
    }
}
