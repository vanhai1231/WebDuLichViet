package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.*;
import com.hutech.DAMH.service.DestinationService;
import com.hutech.DAMH.service.TinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/DuLichViet")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TinhService tinhService;

    @GetMapping("/Destination")
    public String viewDestination(@RequestParam(value = "province", required = false)
                                      String provinceId,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "4") int size, Model model) {
        // Kiểm tra nếu provinceId không null và không trống thì lọc theo tỉnh
        if (provinceId != null && !provinceId.isEmpty()) {
            List<Destination> destinations = destinationService.getDestinationsByProvince(provinceId);
            model.addAttribute("attractions", destinations);
        } else {
            // Nếu không có provinceId, lấy tất cả các điểm đến
//            Page<Destination> destinations = destinationService.getDestinationsByPage(page, size);
            Page<Destination> destinationPage = destinationService.getDestinationsByPage(page, size);
            List<Destination> destinations = destinationPage.getContent();
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", destinationPage.getTotalPages());
            model.addAttribute("pageNumbers", IntStream.range(0, destinationPage.getTotalPages()).boxed().collect(Collectors.toList()));
            model.addAttribute("attractions", destinations);
        }

        List<Tinh> tinhList = tinhService.getAllTinh();
        model.addAttribute("tinhList", tinhList);

        // Trả về tên view template (thường là tên của file HTML Thymeleaf)
        return "index/Destination";
    }
    @PostMapping("/FilterDestination")
    @ResponseBody // Trả về JSON
    public ResponseEntity<Map<String, Object>> viewDestinationPost(@RequestParam(value = "province", required = false) String provinceId) {
        // Lấy danh sách điểm đến theo provinceId
        List<Destination> destinations = destinationService.getDestinationsByProvince(provinceId);

        // Lấy danh sách tỉnh
        List<Tinh> tinhList = tinhService.getAllTinh();

        // Tạo response JSON
        Map<String, Object> response = new HashMap<>();
        response.put("attractions", destinations);
        response.put("tinhList", tinhList);

        return ResponseEntity.ok(response);
    }
}
